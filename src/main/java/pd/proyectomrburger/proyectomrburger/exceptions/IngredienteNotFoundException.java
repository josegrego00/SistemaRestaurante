package pd.proyectomrburger.proyectomrburger.exceptions;

public class IngredienteNotFoundException extends RuntimeException {
    public IngredienteNotFoundException(Long id) {
        super("Ingrediente con ID " + id + " no encontrado");
    }
}
