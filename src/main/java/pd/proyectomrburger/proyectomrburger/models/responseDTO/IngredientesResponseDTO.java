package pd.proyectomrburger.proyectomrburger.models.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientesResponseDTO {

    private Long id;
    private String nombreIngrediente;
    private String unidadMedida;
    private Double costoUnidad;
    private Double stockActual;
    private Double stockMinimo;
    private Boolean activo;
}
