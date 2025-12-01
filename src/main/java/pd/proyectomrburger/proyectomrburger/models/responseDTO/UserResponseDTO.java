package pd.proyectomrburger.proyectomrburger.models.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String username;
    private String role;
    private boolean enabled;

}
