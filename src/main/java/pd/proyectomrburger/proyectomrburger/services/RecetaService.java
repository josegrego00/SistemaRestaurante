package pd.proyectomrburger.proyectomrburger.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pd.proyectomrburger.proyectomrburger.mapper.RecetaMapper;
import pd.proyectomrburger.proyectomrburger.models.Ingrediente;
import pd.proyectomrburger.proyectomrburger.models.Receta;
import pd.proyectomrburger.proyectomrburger.models.RecetaIngrediente;
import pd.proyectomrburger.proyectomrburger.models.requestDTO.RecetaIngredienteRequestDTO;
import pd.proyectomrburger.proyectomrburger.repositories.RecetaRepository;
import pd.proyectomrburger.proyectomrburger.repositories.IngredienteRepository;
import pd.proyectomrburger.proyectomrburger.repositories.RecetaIngredienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final RecetaIngredienteRepository recetaIngredienteRepository;
    private final IngredienteRepository ingredienteRepository; // Usar Repository, no Service
    // private final RecetaMapper recetaMapper; // Si usas DTOs

    public Receta crearRecetaConIngredientes(Receta receta,
            List<RecetaIngredienteRequestDTO> ingredientesDTO) {
        // 1. Guardar la receta
        Receta recetaGuardada = recetaRepository.save(receta);

        // 2. Calcular costo total
        Double costoTotal = calcularCostoTotal(ingredientesDTO);
        recetaGuardada.setCostoReceta(costoTotal); // Si tu entidad tiene este campo
        recetaGuardada = recetaRepository.save(recetaGuardada);
        // 3. Crear lista de RecetaIngrediente
        List<RecetaIngrediente> ingredientes = new ArrayList<>();

        // 3. Guardar los ingredientes de la receta
        for (RecetaIngredienteRequestDTO ingredienteDTO : ingredientesDTO) {
            RecetaIngrediente ri = new RecetaIngrediente();
            ri.setReceta(recetaGuardada);
            ri.setIngrediente(ingredienteRepository.getReferenceById(ingredienteDTO.getIngredienteId()));
            ri.setCantidadNecesaria(ingredienteDTO.getCantidadNecesaria());
            ingredientes.add(ri);
        }

        recetaIngredienteRepository.saveAll(ingredientes);
        return recetaGuardada;
    }

    private Double calcularCostoTotal(List<RecetaIngredienteRequestDTO> ingredientesDTO) {
        return ingredientesDTO.stream()
                .mapToDouble(dto -> {
                    Ingrediente ingrediente = ingredienteRepository.findById(dto.getIngredienteId())
                            .orElseThrow(
                                    () -> new RuntimeException("Ingrediente no encontrado: " + dto.getIngredienteId()));
                    return ingrediente.getCostoUnidad() * dto.getCantidadNecesaria();
                })
                .sum();
    }

    // 4. ACTUALIZAR RECETA COMPLETA (nombre, descripción, ingredientes)
    public Receta actualizarRecetaCompleta(Long id,
            String nombre,
            String descripcion,
            Map<Long, Double> nuevosIngredientes) {
        Receta receta = buscarPorId(id);
        receta.setNombre(nombre);
        receta.setDescripcion(descripcion);

        // Limpiar ingredientes actuales
        recetaIngredienteRepository.deleteById(id);

        return recetaRepository.save(receta);
    }

    public List<Receta> listarRecetas() {
        List<Receta> recetas = recetaRepository.findAll();
        return recetas;
    }

    // 6. VALIDAR STOCK SUFICIENTE PARA PRODUCIR
    public boolean validarStockSuficiente(Long recetaId, Integer porciones) {
        Receta receta = buscarPorId(recetaId);

        return receta.getIngredientes().stream()
                .allMatch(ri -> {
                    Double stockNecesario = ri.getCantidadNecesaria() * porciones;
                    Double stockActual = ri.getIngrediente().getStockActual();
                    return stockActual >= stockNecesario;
                });
    }

    public Receta buscarPorId(Long id) {
        return recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + id));
    }

    private void calcularYCargarCostoReceta(Receta receta) {
        // Obtener los ingredientes de la receta
        List<RecetaIngrediente> ingredientes = recetaIngredienteRepository.findByRecetaId(receta.getId());

        // Calcular costo total
        Double costoTotal = ingredientes.stream()
                .mapToDouble(ri -> {
                    Ingrediente ingrediente = ri.getIngrediente();
                    return ingrediente.getCostoUnidad() * ri.getCantidadNecesaria();
                })
                .sum();

        // Opción 1: Si tienes campo en entidad
        if (receta.getCostoReceta() == null) {
            receta.setCostoReceta(costoTotal);
        }

    }

    // Método para una sola receta
    public Receta obtenerRecetaConCosto(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));
        calcularYCargarCostoReceta(receta);
        return receta;
    }

}