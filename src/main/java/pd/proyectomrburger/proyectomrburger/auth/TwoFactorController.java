package pd.proyectomrburger.proyectomrburger.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pd.proyectomrburger.proyectomrburger.config.CustomUserDetails;
import pd.proyectomrburger.proyectomrburger.models.DTO.TwoFactorDTO;
import pd.proyectomrburger.proyectomrburger.models.User;
import pd.proyectomrburger.proyectomrburger.services.TwoFactorService;
import pd.proyectomrburger.proyectomrburger.services.UserServices;

@RestController
@RequestMapping("/api/2fa")
public class TwoFactorController {

    @Autowired
    private TwoFactorService twoFactorService;

    @Autowired
    private UserServices userServices;

    /**
     * 🔐 CONFIGURAR 2FA - Paso 1: Generar clave y QR
     * ¿QUÉ hace?
     * - Genera una clave secreta NUEVA para el usuario
     * - Crea URL QR para que el usuario escanee
     * - NO activa 2FA todavía (solo prepara todo)
     */
    @PostMapping("/setup")
    public ResponseEntity<?> setupTwoFactor(@AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            System.out.println("🎯 Iniciando configuración 2FA para: " + userDetails.getUsername());
            
            // 1. Generar clave secreta
            String secret = twoFactorService.generarClaveSecreta();
            System.out.println("🗝️ Clave secreta generada: " + secret);
            
            // 2. Generar URL del QR
            String qrCodeUrl = twoFactorService.generarUrlQR(userDetails.getUsername(), secret);
            System.out.println("📱 URL QR generada");
            
            // 3. Preparar respuesta
            TwoFactorDTO response = new TwoFactorDTO();
            response.setEnabled(false); // Aún no está activado
            response.setSecret(secret);
            response.setQrCodeUrl(qrCodeUrl);
            
            System.out.println("✅ Configuración 2FA preparada para: " + userDetails.getUsername());
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            System.out.println("❌ Error en setup 2FA: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error configurando 2FA: " + e.getMessage());
        }
    }

    /**
     * ✅ ACTIVAR 2FA - Paso 2: Verificar código y activar
     * ¿QUÉ hace?
     * - Verifica que el usuario escaneó correctamente el QR
     * - Si el código es válido, ACTIVA el 2FA en la base de datos
     */
    @PostMapping("/verify")
    public ResponseEntity<?> verifyAndEnable(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody TwoFactorDTO twoFactorDTO) {
        try {
            System.out.println("🎯 Verificando código 2FA para: " + userDetails.getUsername());
            System.out.println("🔢 Código recibido: " + twoFactorDTO.getVerificationCode());
            System.out.println("🗝️ Secreto a verificar: " + twoFactorDTO.getSecret());
            
            // 1. Verificar que el código es correcto
            boolean isValid = twoFactorService.verificarCodigo(
                twoFactorDTO.getSecret(), 
                twoFactorDTO.getVerificationCode()
            );
            
            if (isValid) {
                System.out.println("✅ Código 2FA válido");
                
                // 2. Buscar usuario en BD
                User user = userServices.getUserByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                
                // 3. Activar 2FA en el usuario
                user.setTwoFactorEnabled(true);
                user.setTwoFactorSecret(twoFactorDTO.getSecret());
                userServices.updateUser(user);
                
                System.out.println("🎉 2FA activado para usuario: " + user.getUsername());
                
                // 4. Preparar respuesta
                TwoFactorDTO response = new TwoFactorDTO();
                response.setEnabled(true);
                response.setSecret(twoFactorDTO.getSecret()); // Por seguridad, podrías no enviar esto
                
                return ResponseEntity.ok(response);
            } else {
                System.out.println("❌ Código 2FA inválido");
                return ResponseEntity.badRequest().body("Código de verificación inválido");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error verificando 2FA: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error verificando 2FA: " + e.getMessage());
        }
    }

    /**
     * 🚫 DESACTIVAR 2FA
     * ¿QUÉ hace?
     * - Desactiva el 2FA para el usuario
     * - Elimina la clave secreta de la base de datos
     */
    @PostMapping("/disable")
    public ResponseEntity<?> disableTwoFactor(@AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            System.out.println("🎯 Desactivando 2FA para: " + userDetails.getUsername());
            
            // 1. Buscar usuario
            User user = userServices.getUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            // 2. Desactivar 2FA
            user.setTwoFactorEnabled(false);
            user.setTwoFactorSecret(null); // Importante: eliminar la clave
            userServices.updateUser(user);
            
            System.out.println("✅ 2FA desactivado para: " + user.getUsername());
            return ResponseEntity.ok("2FA desactivado correctamente");
            
        } catch (Exception e) {
            System.out.println("❌ Error desactivando 2FA: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error desactivando 2FA: " + e.getMessage());
        }
    }

    /**
     * ℹ️ ESTADO 2FA
     * ¿QUÉ hace?
     * - Dice si el usuario tiene 2FA activado o no
     * - Útil para que el frontend sepa qué mostrar
     */
    @GetMapping("/status")
    public ResponseEntity<?> getTwoFactorStatus(@AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            TwoFactorDTO response = new TwoFactorDTO();
            response.setEnabled(userDetails.isTwoFactorEnabled());
            
            // Por seguridad, NO enviamos el secret
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error obteniendo estado 2FA");
        }
    }
}