package pd.proyectomrburger.proyectomrburger.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pd.proyectomrburger.proyectomrburger.models.DTO.UserDTO;
import pd.proyectomrburger.proyectomrburger.models.requestDTO.UserRequestDTO;
import pd.proyectomrburger.proyectomrburger.models.responseDTO.UserResponseDTO;
import pd.proyectomrburger.proyectomrburger.models.User;
import pd.proyectomrburger.proyectomrburger.services.UserServices;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        try {

            // Verificar si el usuario ya existe
            if (userServices.getUserByUsername(userRequestDTO.getUsername()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }


            //esto lo debe Hacer una clase de Mapper----- (Ojo para mejorar)

            // Crear nuevo usuario
            User user = new User();
            user.setUsername(userRequestDTO.getUsername());
            user.setEmail(userRequestDTO.getEmail());
            user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            user.setRole(userRequestDTO.getRole() != null ? userRequestDTO.getRole() : "USER");

            // Guardar usuario
            User savedUser = userServices.saveUser(user);

            // Respuesta sin el password
            UserResponseDTO responseDTO = new UserResponseDTO();
            responseDTO.setUsername(savedUser.getUsername());
            responseDTO.setEmail(savedUser.getEmail());
            responseDTO.setEnabled(true);
            responseDTO.setRole(savedUser.getRole());

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/debug")
    public String debug() {
        System.out.println("🎯 DEBUG ENDPOINT LLAMADO!");
        System.out.println("✅ SecurityConfig permite este endpoint");
        return "Debug funciona! Revisa la consola de Spring Boot";
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("🎯 TEST ENDPOINT LLAMADO!");
        return "User Controller is working!";
    }
}