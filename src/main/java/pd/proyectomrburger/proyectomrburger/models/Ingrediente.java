package pd.proyectomrburger.proyectomrburger.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreIngrediente;

    @Column(nullable = false)
    private String unidadMedida;

    @Column(nullable = false)
    private Double costoUnidad;

    @Column(nullable = false)
    private Double stockActual;

    @Column(nullable = false)
    private Double stockMinimo=0.0;
    
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean activo=true;



}
