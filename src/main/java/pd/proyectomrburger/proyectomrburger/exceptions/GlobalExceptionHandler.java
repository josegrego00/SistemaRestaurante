package pd.proyectomrburger.proyectomrburger.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoIngredientesDisponiblesException.class)
    public ResponseEntity<ErrorEnApi> handleNoIngredientes(NoIngredientesDisponiblesException ex) {
        ErrorEnApi error = new ErrorEnApi(
                HttpStatus.NOT_FOUND.value(),
                "No hay ingredientes",
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IngredienteNotFoundException.class)
    public ResponseEntity<ErrorEnApi> handleIngredienteNotFound(IngredienteNotFoundException ex) {
        ErrorEnApi error = new ErrorEnApi(
                HttpStatus.NOT_FOUND.value(),
                "Ingrediente no encontrado",
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
