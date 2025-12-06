package pd.proyectomrburger.proyectomrburger.models.requestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaIngredienteRequestDTO {
    @NotNull(message = "Debe seleccionar un ingrediente")
    private Long ingredienteId;

    @NotNull(message = "La cantidad es requerida")
    private Double cantidadNecesaria;
}
