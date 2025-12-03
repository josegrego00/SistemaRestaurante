package pd.proyectomrburger.proyectomrburger.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pd.proyectomrburger.proyectomrburger.models.RecetaIngrediente;

public interface RecetaIngredienteRepository extends JpaRepository<RecetaIngrediente, Long> {

    List<RecetaIngrediente> findByRecetaId(Long recetaId);

    
}
