package pd.proyectomrburger.proyectomrburger.models.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecetaIngredienteResponseDTO {
    private Long id;
    private String nombreIngrediente;
    private String unidadMedida;
    private Double cantidadNecesaria;
    private Double costoSubtotal;
}
