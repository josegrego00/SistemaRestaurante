package pd.proyectomrburger.proyectomrburger.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import pd.proyectomrburger.proyectomrburger.mapper.IngredienteMapper;
import pd.proyectomrburger.proyectomrburger.mapper.RecetaMapper;
import pd.proyectomrburger.proyectomrburger.models.Ingrediente;
import pd.proyectomrburger.proyectomrburger.models.Receta;
import pd.proyectomrburger.proyectomrburger.models.requestDTO.RecetaRequestDTO;
import pd.proyectomrburger.proyectomrburger.models.responseDTO.IngredientesResponseDTO;
import pd.proyectomrburger.proyectomrburger.models.responseDTO.RecetaResponseDTO;
import pd.proyectomrburger.proyectomrburger.services.RecetaService;
import pd.proyectomrburger.proyectomrburger.services.IngredienteService;

@Controller
@RequestMapping("/recetas")
@RequiredArgsConstructor

public class RecetaViewController {
    private final RecetaService recetaService;
    private final IngredienteService ingredienteService;
    private final IngredienteMapper ingredienteMapper;
    private final RecetaMapper recetaMapper;

    @GetMapping
    public String listarRecetas(Model model) {

        List<RecetaResponseDTO> recetas = new ArrayList<>();
        for (Receta r : recetaService.listarRecetas()) {
            RecetaResponseDTO recetaResponseDTO = recetaMapper.toResponseDTO(r);
            recetas.add(recetaResponseDTO);
        }
        model.addAttribute("recetas", recetas);
        model.addAttribute("titulo", "🍔 Inventario de Recetas");
        model.addAttribute("title", "Recetas - Mr Burger");
        model.addAttribute("content", "recetas/index");
        return "layout";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        List<IngredientesResponseDTO> ingredientes = new ArrayList<>();
        for (Ingrediente in : ingredienteService.listarTodos()) {
            IngredientesResponseDTO iResponseDTO = ingredienteMapper.toResponseDTO(in);
            ingredientes.add(iResponseDTO);
        }
        
        model.addAttribute("recetaRequest", new RecetaRequestDTO());
        model.addAttribute("ingredientes", ingredientes); 
        model.addAttribute("title", "Crear Receta - Mr Burger");
        model.addAttribute("titulo", "➕ Nueva Receta");
        model.addAttribute("content", "recetas/crear");
        return "layout";
    }

    @PostMapping("/guardar")
    public String guardarReceta(@ModelAttribute("recetaRequest") RecetaRequestDTO entityDto) {
        System.out.println("=== DEBUG RECETA ===");
        System.out.println("Nombre: " + entityDto.getNombre());
        System.out.println("Descripción: " + entityDto.getDescripcion());
        System.out.println("Ingredientes es null? " + (entityDto.getIngredientes() == null));

        if (entityDto.getIngredientes() != null) {
            System.out.println("Cantidad ingredientes: " + entityDto.getIngredientes().size());
            for (int i = 0; i < entityDto.getIngredientes().size(); i++) {
                var ing = entityDto.getIngredientes().get(i);
                System.out.println("Ingrediente " + i + ": ID=" + ing.getIngredienteId() +
                        ", Cantidad=" + ing.getCantidadNecesaria());
            }
        } else {
            System.out.println("ERROR: ingredientes es NULL!");
        }

        Receta receta = recetaMapper.toEntity(entityDto);
        recetaService.crearRecetaConIngredientes(receta, entityDto.getIngredientes());
        return "redirect:/recetas";
    }
}