package pd.proyectomrburger.proyectomrburger.models.requestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRequestDTO {

    @NotBlank(message = "El Username es Obligatorio")
    private String username;

    @NotBlank(message = "El Email es Obligatorio")
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String role;

    public UserRequestDTO() {
    }

    
    public UserRequestDTO(@NotBlank(message = "El Username es Obligatorio") String username,
            @NotBlank(message = "El Email es Obligatorio") @Email String email, @NotBlank String password,
            @NotBlank String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
