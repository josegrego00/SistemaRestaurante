package pd.proyectomrburger.proyectomrburger.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pd.proyectomrburger.proyectomrburger.models.User;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convierte "ADMIN" -> "ROLE_ADMIN" para Spring Security
        return Collections.singletonList(
            new SimpleGrantedAuthority("ROLE_" + user.getRole())
        );
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Puedes cambiar esto si manejas expiración de cuentas
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Puedes cambiar esto si manejas bloqueo de cuentas
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Puedes cambiar esto si manejas expiración de credenciales
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
    
    // Métodos adicionales útiles
    public String getEmail() {
        return user.getEmail();
    }
    
    public String getRole() {
        return user.getRole();
    }
    
    public Long getId() {
        return user.getId();
    }
    
    // Para acceder al usuario completo si lo necesitas
    public User getUser() {
        return user;
    }

      // 🆕 NUEVO MÉTODO PARA 2FA
    /**
     * 🔐 Verifica si el usuario tiene 2FA activado
     * ¿POR QUÉ necesitamos esto?
     * - Para que el Controller pueda preguntar: "¿Este usuario tiene 2FA activo?"
     * - Para el flujo de login: si es true, pedir código de 6 dígitos
     */
    public boolean isTwoFactorEnabled() {
        return user.isTwoFactorEnabled();
    }
    
    // 🆕 NUEVO MÉTODO PARA OBTENER EL SECRETO
    /**
     * 🗝️ Obtiene la clave secreta del 2FA
     * ¿POR QUÉ necesitamos esto?
     * - Para verificar códigos durante el login
     * - Solo se usa si isTwoFactorEnabled() = true
     */
    public String getTwoFactorSecret() {
        return user.getTwoFactorSecret();
    }
}