package pd.proyectomrburger.proyectomrburger.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pd.proyectomrburger.proyectomrburger.models.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{

    List<Ingrediente> findByUnidadMedida(String unidadMedida);


    
}
