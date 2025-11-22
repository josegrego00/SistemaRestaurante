package pd.proyectomrburger.proyectomrburger.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TwoFactorAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
                                      HttpServletResponse response, 
                                      Authentication authentication) throws IOException, ServletException {
        
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        System.out.println("🎯 AuthenticationSuccess - Usuario: " + userDetails.getUsername());
        System.out.println("🔐 2FA activado: " + userDetails.isTwoFactorEnabled());
        
        if (userDetails.isTwoFactorEnabled()) {
            // 🎯 Usuario tiene 2FA activado - Guardar en sesión temporal y redirigir a verificación
            request.getSession().setAttribute("TEMP_AUTHENTICATED_USER", userDetails);
            
            // Limpiar el contexto de seguridad (no está completamente autenticado todavía)
            SecurityContextHolder.getContext().setAuthentication(null);
            
            System.out.println("📱 Redirigiendo a verificación 2FA");
            response.sendRedirect("/verify-2fa");
        } else {
            // 🎯 Usuario NO tiene 2FA - Redirigir directamente al dashboard
            System.out.println("🚀 Sin 2FA - Redirigiendo a dashboard");
            response.sendRedirect("/dashboard");
        }
    }
}