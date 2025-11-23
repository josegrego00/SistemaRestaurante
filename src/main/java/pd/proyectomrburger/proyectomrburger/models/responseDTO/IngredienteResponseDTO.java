package pd.proyectomrburger.proyectomrburger.models.responseDTO;

import jakarta.persistence.Column;

public class IngredienteResponseDTO {

    private Long id;
    private String nombreIngrediente;
    private String unidadMedida;
    private Double costoUnidad;

    public IngredienteResponseDTO() {
    }

    public IngredienteResponseDTO(Long id, String nombreIngrediente, String unidadMedida, Double costoUnidad) {
        this.id = id;
        this.nombreIngrediente = nombreIngrediente;
        this.unidadMedida = unidadMedida;
        this.costoUnidad = costoUnidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
