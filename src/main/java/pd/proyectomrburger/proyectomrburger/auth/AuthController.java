package pd.proyectomrburger.proyectomrburger.auth;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/home")
    public String showDashboard() {
        return "dashboard"; // Página después del login
    }
}