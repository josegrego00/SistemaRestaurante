package pd.proyectomrburger.proyectomrburger.services;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TwoFactorService {
    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();
    Logger logger = org.slf4j.LoggerFactory.getLogger(TwoFactorService.class);

    // 🗝️ Generar clave secreta
    public String generarClaveSecreta() {
        return gAuth.createCredentials().getKey();
    }

    // 📱 Generar URL para QR
    public String generarUrlQR(String username, String secret) {
        String issuer = "Mr Burger";
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL(issuer, username,
                new GoogleAuthenticatorKey.Builder(secret).build());
    }

    // ✅ Verificar código
    public boolean verificarCodigo(String secret, int codigo) {
        
        return gAuth.authorize(secret, codigo);
    }

    // 🛡️ Verificación segura
    public boolean esCodigoValido(String secret, int codigo) {
        try {
            return gAuth.authorize(secret, codigo);
        } catch (Exception e) {
            
            return false;
        }
    }
}