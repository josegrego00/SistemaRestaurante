package pd.proyectomrburger.proyectomrburger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pd.proyectomrburger.proyectomrburger.config.CustomUserDetails;
import pd.proyectomrburger.proyectomrburger.models.User;
import pd.proyectomrburger.proyectomrburger.repositories.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        // 2. Convierte tu User Entity a UserDetails
        return new CustomUserDetails(user); // ← El que acabamos de crear
    }

}
