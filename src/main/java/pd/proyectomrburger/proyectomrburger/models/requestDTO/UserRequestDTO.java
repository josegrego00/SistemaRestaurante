package pd.proyectomrburger.proyectomrburger.models.requestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    
    private int code;
    

}
