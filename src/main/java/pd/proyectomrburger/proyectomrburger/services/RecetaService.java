package pd.proyectomrburger.proyectomrburger.services;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import pd.proyectomrburger.proyectomrburger.models.Receta;
import pd.proyectomrburger.proyectomrburger.models.RecetaIngrediente;
import pd.proyectomrburger.proyectomrburger.repositories.RecetaRepository;
import pd.proyectomrburger.proyectomrburger.repositories.RecetaIngredienteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final RecetaIngredienteRepository recetaIngredienteRepository;
    private final IngredienteService ingredienteService;

    public Receta crearReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    public List<Receta> listarTodas() {
        return recetaRepository.findAll();
    }

    public Receta buscarPorId(Long id) {
        return recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + id));
    }

    public Receta actualizarReceta(Long id, Receta recetaActualizada) {
        Receta receta = buscarPorId(id);
        receta.setNombre(recetaActualizada.getNombre());
        receta.setDescripcion(recetaActualizada.getDescripcion());
        return recetaRepository.save(receta);
    }

    public void eliminarReceta(Long id) {
        Receta receta = buscarPorId(id);
        recetaRepository.delete(receta);
    }

    public void agregarIngredienteAReceta(Long recetaId, Long ingredienteId, Double cantidad) {
        Receta receta = buscarPorId(recetaId);
        RecetaIngrediente recetaIngrediente = new RecetaIngrediente();
        recetaIngrediente.setReceta(receta);
        recetaIngrediente.setIngrediente(ingredienteService.buscarPorId(ingredienteId));
        recetaIngrediente.setCantidadNecesaria(cantidad);
        recetaIngredienteRepository.save(recetaIngrediente);
    }

    public void removerIngredienteDeReceta(Long recetaIngredienteId) {
        recetaIngredienteRepository.deleteById(recetaIngredienteId);
    }

    public List<RecetaIngrediente> obtenerIngredientesDeReceta(Long recetaId) {
        return recetaIngredienteRepository.findByRecetaId(recetaId);
    }
}