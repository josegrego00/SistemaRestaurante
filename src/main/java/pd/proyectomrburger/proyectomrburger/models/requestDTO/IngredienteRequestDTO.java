package pd.proyectomrburger.proyectomrburger.models.requestDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class IngredienteRequestDTO {

    @NotBlank(message = "El nombre del ingrediente no puede estar vacío")
    private String nombreIngrediente;

    @NotBlank(message = "La unidad de medida no puede estar vacía")
    @Size(min = 2, message = "La unidad de medida tiene que ser algo Coherente")
    private String unidadMedida;

    @NotNull(message = "El costo por unidad no puede ser nulo")
    @Min(value = 0, message = "El costo no puedes ser negativo")
    private Double costoUnidad;

    @NotNull(message = "El stock actual no puede ser nulo")
    @Min(value = 0, message = "El stock actual no puede ser negativo")
    private Double stockActual;

    private Double stockMinimo = 1.0;
    private Boolean activo = true;

    public IngredienteRequestDTO() {
    }

    public IngredienteRequestDTO(
            @NotBlank(message = "El nombre del ingrediente no puede estar vacío") String nombreIngrediente,
            @NotBlank(message = "La unidad de medida no puede estar vacía") @Size(min = 2, message = "La unidad de medida tiene que ser algo Coherente") String unidadMedida,
            @NotNull(message = "El costo por unidad no puede ser nulo") @Min(value = 0, message = "El costo no puedes ser negativo") Double costoUnidad,
            @NotNull(message = "El stock actual no puede ser nulo") @Min(value = 0, message = "El stock actual no puede ser negativo") Double stockActual) {
        this.nombreIngrediente = nombreIngrediente;
        this.unidadMedida = unidadMedida;
        this.costoUnidad = costoUnidad;
        this.stockActual = stockActual;

    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Double getCostoUnidad() {
        return costoUnidad;
    }

    public void setCostoUnidad(Double costoUnidad) {
        this.costoUnidad = costoUnidad;
    }

    public Double getStockActual() {
        return stockActual;
    }

    public void setStockActual(Double stockActual) {
        this.stockActual = stockActual;
    }

    public Double getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Double stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

}
