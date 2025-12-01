package pd.proyectomrburger.proyectomrburger.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import pd.proyectomrburger.proyectomrburger.models.Ingrediente;
import pd.proyectomrburger.proyectomrburger.models.requestDTO.IngredienteRequestDTO;
import pd.proyectomrburger.proyectomrburger.repositories.IngredienteRepository;

@Service
@AllArgsConstructor
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public Ingrediente crearIngrediente(Ingrediente ingrediente) {

        return ingredienteRepository.save(ingrediente);
    }

    public List<Ingrediente> listarTodos() {
        return ingredienteRepository.findAll();
    }

    public Optional<Ingrediente> buscarPorId(Long id) {
        return ingredienteRepository.findById(id);

    }

    public List<Ingrediente> buscarPorUnidadMedida(String unidadMedida) {
        return ingredienteRepository.findByUnidadMedida(unidadMedida);
    }

    public Ingrediente actualizar(Long id, IngredienteRequestDTO requestDTO) {

        Optional<Ingrediente> ingreOptional = buscarPorId(id);
        Ingrediente ingrediente = ingreOptional.get();
        // Actualizar campos
        ingrediente.setNombreIngrediente(requestDTO.getNombreIngrediente());
        ingrediente.setUnidadMedida(requestDTO.getUnidadMedida());
        ingrediente.setCostoUnidad(requestDTO.getCostoUnidad());
        ingrediente.setStockActual(requestDTO.getStockActual());
        ingrediente.setStockMinimo(requestDTO.getStockMinimo());
        ingrediente.setActivo(requestDTO.getActivo());

        return ingredienteRepository.save(ingrediente);
    }

    public void desactivar(Long id) {
        Optional<Ingrediente> ingreOptional = buscarPorId(id);
        Ingrediente ingrediente = ingreOptional.get();
        ingrediente.setActivo(false);
        ingredienteRepository.save(ingrediente);
    }

    

}
