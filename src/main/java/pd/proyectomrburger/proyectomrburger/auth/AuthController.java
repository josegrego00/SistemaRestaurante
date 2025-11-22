package pd.proyectomrburger.proyectomrburger.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // login.html con Thymeleaf
    }

    @GetMapping("/verify-2fa")
    public String showVerify2FAForm() {
        return "verify-2fa";
    }
/*
    @PostMapping("/login")
    public String processLogin() {
        // Spring Security maneja la autenticación
        return "redirect:/dashboard";
    }
*/
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard"; // Página después del login
    }
}