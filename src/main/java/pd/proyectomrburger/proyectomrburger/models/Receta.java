package pd.proyectomrburger.proyectomrburger.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<RecetaIngrediente> ingredientes;

    private Double costoReceta;
}
