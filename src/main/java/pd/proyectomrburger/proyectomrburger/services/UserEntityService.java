package pd.proyectomrburger.proyectomrburger.services;

import org.springframework.stereotype.Service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

import lombok.AllArgsConstructor;
import pd.proyectomrburger.proyectomrburger.models.UserEntity;
import pd.proyectomrburger.proyectomrburger.models.requestDTO.UserRequestDTO;
import pd.proyectomrburger.proyectomrburger.repositories.UserEntityRepository;

@Service
@AllArgsConstructor
public class UserEntityService {

    private GoogleAuthenticator gAuth = new GoogleAuthenticator();
    private UserEntityRepository userEntityRepository;

    // creaccion de secretKey
    public String generarSecretkey() {
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        return key.getKey();
    }

    public UserEntity createUser(UserEntity user){
        return userEntityRepository.save(user);
    }

     // validad si la clave secreta es correcta 
    public boolean isValidSecrerKey(String key, int code) {
        return gAuth.authorize(key, code);
    }


    public String getSecretKey(UserRequestDTO dto) {
        UserEntity entity = userEntityRepository.findByUsername(dto.getUsername()).orElseThrow(()->new RuntimeException("Usuario no encontrado"));
        return entity.getSecretKey();
    }


}
