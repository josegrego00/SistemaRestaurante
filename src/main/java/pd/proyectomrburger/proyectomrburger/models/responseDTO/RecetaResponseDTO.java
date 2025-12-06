package pd.proyectomrburger.proyectomrburger.models.responseDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecetaResponseDTO {
    
    private Long id;
    private String nombre;
    private Double costoTotal;
    private List<RecetaIngredienteResponseDTO> ingredientes;
}
