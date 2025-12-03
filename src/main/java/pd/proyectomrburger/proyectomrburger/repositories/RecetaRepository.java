package pd.proyectomrburger.proyectomrburger.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pd.proyectomrburger.proyectomrburger.models.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
    List<Receta> findByNombreContainingIgnoreCase(String nombre);

}
