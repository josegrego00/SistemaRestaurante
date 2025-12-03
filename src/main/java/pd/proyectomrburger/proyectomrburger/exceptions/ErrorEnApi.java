package pd.proyectomrburger.proyectomrburger.exceptions;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorEnApi {

    private int status; // 400, 404, 500, etc.
    private String error; // "Bad Request", "Not Found", etc.
    private String message; // Mensaje específico del error
    private String path; // URL donde ocurrió el error (opcional)

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp; // Cuándo ocurrió

    // Constructor sin path (más simple)
    public ErrorEnApi(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Constructor con path
    public ErrorEnApi(int status, String error, String message, String path) {
        this(status, error, message);
        this.path = path;
    }

}
