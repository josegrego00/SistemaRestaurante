package pd.proyectomrburger.proyectomrburger.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pd.proyectomrburger.proyectomrburger.exceptions.IngredienteNotFoundException;
import pd.proyectomrburger.proyectomrburger.exceptions.NoIngredientesDisponiblesException;
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
        List<Ingrediente> ingredientesLista = ingredienteRepository.findAll();
        if (ingredientesLista.isEmpty()) {
            throw new NoIngredientesDisponiblesException(
                    "El sistema no tiene ingredientes registrados. Por favor, agregue al menos uno.");
        }
        return ingredientesLista.stream()
                .filter(Ingrediente::getActivo)
                .toList();
    }

    public Ingrediente buscarPorId(Long id) {
        return ingredienteRepository.findById(id)
                .orElseThrow(() -> new IngredienteNotFoundException(id));
    }

    public List<Ingrediente> buscarPorUnidadMedida(String unidadMedida) {
        return ingredienteRepository.findByUnidadMedida(unidadMedida);
    }

    public Ingrediente actualizar(Long id, Ingrediente ingredienteN) {

        Ingrediente ingrediente = buscarPorId(id);
        ingrediente.setNombreIngrediente(ingredienteN.getNombreIngrediente());
        return ingredienteRepository.save(ingrediente);
    }

    public void desactivar(Long id) {
        Ingrediente ingrediente = buscarPorId(id);
        ingrediente.setActivo(false);
        ingredienteRepository.save(ingrediente);
    }

    public void reactivar(Long id) {
        Ingrediente ingrediente = buscarPorId(id);
        ingrediente.setActivo(true);
        ingredienteRepository.save(ingrediente);
    }

}
