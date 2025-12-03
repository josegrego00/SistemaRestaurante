package pd.proyectomrburger.proyectomrburger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import pd.proyectomrburger.proyectomrburger.models.Receta;
import pd.proyectomrburger.proyectomrburger.services.RecetaService;
import pd.proyectomrburger.proyectomrburger.services.IngredienteService;

@Controller
@RequestMapping("/recetas")
@RequiredArgsConstructor
public class RecetaViewController {

    private final RecetaService recetaService;
    private final IngredienteService ingredienteService;

    @GetMapping
    public String listarRecetas(Model model) {
        model.addAttribute("title", "Recetas - Mr Burger");
        model.addAttribute("titulo", "🍔 Recetas del Menú");
        model.addAttribute("content", "recetas/index");
        model.addAttribute("sidebarActive", "recetas"); // ← AÑADE ESTO
        model.addAttribute("recetas", recetaService.listarTodas());
        return "layout";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("title", "Crear Receta - Mr Burger");
        model.addAttribute("titulo", "➕ Nueva Receta");
        model.addAttribute("content", "recetas/crear");
        model.addAttribute("ingredientes", ingredienteService.listarTodos());
        return "layout";
    }
}