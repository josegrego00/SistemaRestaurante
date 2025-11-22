package pd.proyectomrburger.proyectomrburger.models.DTO;

import lombok.Data;

@Data
public class TwoFactorDTO {
    
    /**
     * 🎯 ¿Está activado el 2FA?
     * - false: El usuario no tiene 2FA activado
     * - true: El usuario sí tiene 2FA activado
     */
    private boolean enabled;
    
    /**
     * 🗝️ Clave secreta (16 caracteres)
     * - Solo se envía durante la CONFIGURACIÓN
     * - NO se envía cuando el 2FA ya está activado (por seguridad)
     * - Ejemplo: "JBSWY3DPEHPK3PXP"
     */
    private String secret;
    
    /**
     * 📱 URL del código QR
     * - Solo se envía durante la CONFIGURACIÓN
     * - El frontend convierte esta URL en una imagen QR
     * - Ejemplo: "otpauth://totp/Mr%20Burger:admin?..."
     */
    private String qrCodeUrl;
    
    /**
     * 🔢 Código de verificación de 6 dígitos
     * - Lo escribe el usuario desde Google Authenticator
     * - Solo se usa durante la VERIFICACIÓN
     * - Ejemplo: 123456
     */
    private Integer verificationCode;
}