package pd.proyectomrburger.proyectomrburger.services;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import pd.proyectomrburger.proyectomrburger.models.Producto;
import pd.proyectomrburger.proyectomrburger.models.Receta;
import pd.proyectomrburger.proyectomrburger.repositories.ProductoRepository;
import pd.proyectomrburger.proyectomrburger.repositories.RecetaRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final RecetaRepository recetaRepository;

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto crearProductoConReceta(String nombre, Double precioVenta, Long recetaId) {
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));
        
        Producto producto = Producto.builder()
                .nombre(nombre)
                .precioVenta(precioVenta)
                .tieneReceta(true)
                .receta(receta)
                .build();
        
        return productoRepository.save(producto);
    }

    public Producto crearProductoSinReceta(String nombre, Double precioVenta) {
        Producto producto = Producto.builder()
                .nombre(nombre)
                .precioVenta(precioVenta)
                .tieneReceta(false)
                .receta(null)
                .build();
        
        return productoRepository.save(producto);
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public List<Producto> listarProductosConReceta() {
        return productoRepository.findByTieneRecetaTrue();
    }

    public List<Producto> listarProductosSinReceta() {
        return productoRepository.findByTieneRecetaFalse();
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto producto = buscarPorId(id);
        producto.setNombre(productoActualizado.getNombre());
        producto.setPrecioVenta(productoActualizado.getPrecioVenta());
        producto.setTieneReceta(productoActualizado.isTieneReceta());
        producto.setReceta(productoActualizado.getReceta());
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}