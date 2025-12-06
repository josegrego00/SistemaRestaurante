package pd.proyectomrburger.proyectomrburger.models.requestDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecetaRequestDTO {
    private String nombre;
    private String descripcion;
    private List<RecetaIngredienteRequestDTO> ingredientes;
}
