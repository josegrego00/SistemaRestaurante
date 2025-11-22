package pd.proyectomrburger.proyectomrburger.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pd.proyectomrburger.proyectomrburger.services.TwoFactorService;

import java.io.IOException;

@Component
public class TwoFactorAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TwoFactorService twoFactorService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        
        System.out.println("🎯 TwoFactorFilter - URL: " + request.getRequestURI());
        
        // Solo procesar POST de verify-2fa
        if ("/verify-2fa".equals(request.getRequestURI()) && "POST".equalsIgnoreCase(request.getMethod())) {
            process2FAVerification(request, response, filterChain);
            return;
        }
        
        filterChain.doFilter(request, response);
    }

    private void process2FAVerification(HttpServletRequest request, 
                                      HttpServletResponse response, 
                                      FilterChain filterChain) throws IOException, ServletException {
        
        try {
            String code = request.getParameter("code");
            System.out.println("🔐 Procesando verificación 2FA - Código: " + code);
            
            if (code == null || code.length() != 6) {
                System.out.println("❌ Código inválido (longitud)");
                response.sendRedirect("/verify-2fa?error");
                return;
            }
            
            // Convertir código a número
            int verificationCode = Integer.parseInt(code);
            
            // Obtener usuario de la sesión temporal
            CustomUserDetails userDetails = (CustomUserDetails) request.getSession()
                    .getAttribute("TEMP_AUTHENTICATED_USER");
            
            if (userDetails == null) {
                System.out.println("❌ No hay usuario temporal en sesión");
                response.sendRedirect("/login");
                return;
            }
            
            System.out.println("🔍 Verificando código para: " + userDetails.getUsername());
            System.out.println("🗝️ Secret: " + userDetails.getTwoFactorSecret());
            System.out.println("🔢 Código: " + verificationCode);
            
            // Verificar código 2FA
            boolean isValid = twoFactorService.verificarCodigo(
                userDetails.getTwoFactorSecret(), 
                verificationCode
            );
            
            System.out.println("✅ Resultado verificación: " + isValid);
            
            if (isValid) {
                // ✅ Código correcto - Completar autenticación
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
                // Limpiar sesión temporal
                request.getSession().removeAttribute("TEMP_AUTHENTICATED_USER");
                
                System.out.println("🎉 2FA verificado - Redirigiendo a dashboard");
                response.sendRedirect("/dashboard");
            } else {
                // ❌ Código incorrecto
                System.out.println("❌ Código 2FA inválido");
                response.sendRedirect("/verify-2fa?error");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Error formato código: " + e.getMessage());
            response.sendRedirect("/verify-2fa?error");
        } catch (Exception e) {
            System.out.println("❌ Error en verificación 2FA: " + e.getMessage());
            response.sendRedirect("/verify-2fa?error");
        }
    }
}