package pd.proyectomrburger.proyectomrburger.models.requestDTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredienteRequestDTO {

    @NotBlank(message = "El nombre es Necesario")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombreIngrediente;

    @NotBlank(message = "La unidad de medida es necesario")
    @Size(min = 2, max = 10)
    private String unidadMedida;

    @NotNull(message = "El costo por unidad no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "El costo no puede ser negativo")
    private Double costoUnidad;

    @NotNull(message = "El stock actual no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "El stock actual no puede ser negativo")
    private Double stockActual;

    @DecimalMin(value = "0.0", inclusive = true, message = "El stock mínimo no puede ser negativo")
    private Double stockMinimo = 0.0;

    private Boolean activo = true;

}
