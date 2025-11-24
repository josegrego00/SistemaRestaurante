package pd.proyectomrburger.proyectomrburger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pd.proyectomrburger.proyectomrburger.models.Ingrediente;
import pd.proyectomrburger.proyectomrburger.repositories.IngredienteRepository;

@Service
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    @Autowired
    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository= ingredienteRepository;
    }

    public Ingrediente crearIngrediente(Ingrediente ingrediente){
        
        return ingredienteRepository.save(ingrediente);
    }
    
    
    

}
