package pd.proyectomrburger.proyectomrburger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pd.proyectomrburger.proyectomrburger.models.Producto;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByTieneRecetaTrue();
    List<Producto> findByTieneRecetaFalse();
}