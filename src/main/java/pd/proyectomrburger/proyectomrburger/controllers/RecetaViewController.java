package pd.proyectomrburger.proyectomrburger.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import pd.proyectomrburger.proyectomrburger.mapper.RecetaMapper;
import pd.proyectomrburger.proyectomrburger.models.Receta;
import pd.proyectomrburger.proyectomrburger.models.requestDTO.RecetaRequestDTO;
import pd.proyectomrburger.proyectomrburger.models.responseDTO.RecetaResponseDTO;
import pd.proyectomrburger.proyectomrburger.services.RecetaService;
import pd.proyectomrburger.proyectomrburger.services.IngredienteService;


@Controller
@RequestMapping("/recetas")
@RequiredArgsConstructor

public class RecetaViewController {
    private final RecetaService recetaService;
    private final IngredienteService ingredienteService;
    private final RecetaMapper recetaMapper;

    @GetMapping
    public String listarRecetas(Model model) {

        List<RecetaResponseDTO> recetas = recetaService.listarTodasConCosto().stream()
                .map(recetaMapper::toResponseDTO)
                .collect(Collectors.toList());

        model.addAttribute("recetas", recetas);
        model.addAttribute("titulo", "🍔 Inventario de Recetas");
        model.addAttribute("recetaRequest", new RecetaRequestDTO());
        model.addAttribute("title", "Recetas - Mr Burger");
        model.addAttribute("content", "recetas/index");
        return "layout";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("recetaRequest", new RecetaRequestDTO());
        model.addAttribute("ingredientes", ingredienteService.listarTodos()); // Para select
        model.addAttribute("title", "Crear Receta - Mr Burger");
        model.addAttribute("titulo", "➕ Nueva Receta");
        model.addAttribute("content", "recetas/crear");
        return "layout";
    }

    @PostMapping("/guardar")
    public String guardarReceta(@ModelAttribute RecetaRequestDTO entityDto) {
        Receta receta=recetaMapper.toEntity(entityDto);
        recetaService.crearRecetaConIngredientes(receta, entityDto.getIngredientes());
        return "redirect:/recetas";
    }
    
}