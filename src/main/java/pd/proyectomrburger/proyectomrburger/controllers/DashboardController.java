package pd.proyectomrburger.proyectomrburger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("title", "Dashboard - Mr Burger");
        model.addAttribute("titulo", "📊 Dashboard Principal");
        model.addAttribute("content", "dashboard/index");
        return "layout";
    }
}