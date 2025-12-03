package pd.proyectomrburger.proyectomrburger.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import pd.proyectomrburger.proyectomrburger.mapper.IngredienteMapper;
import pd.proyectomrburger.proyectomrburger.models.Ingrediente;
import pd.proyectomrburger.proyectomrburger.models.requestDTO.IngredienteRequestDTO;
import pd.proyectomrburger.proyectomrburger.models.responseDTO.IngredientesResponseDTO;
import pd.proyectomrburger.proyectomrburger.services.IngredienteService;

@Controller
@RequestMapping("/inventario")
@RequiredArgsConstructor
public class InventarioViewController {

    private final IngredienteService ingredienteService;
    private final IngredienteMapper ingredienteMapper;

    @GetMapping
    public String listarIngredientes(Model model) {

        List<IngredientesResponseDTO> ingredientesResponse = ingredienteService.listarTodos().stream()
                .map(ingredienteMapper::toResponseDTO)
                .collect(Collectors.toList());

        model.addAttribute("title", "Inventario - Mr Burger");
        model.addAttribute("titulo", "📦 Inventario de Ingredientes");
        model.addAttribute("content", "inventario/index");
        model.addAttribute("ingredientes", ingredientesResponse);
        model.addAttribute("ingredienteRequest", new IngredienteRequestDTO());
        return "layout";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("title", "Crear Ingrediente - Mr Burger");
        model.addAttribute("titulo", "➕ Nuevo Ingrediente");
        model.addAttribute("content", "inventario/crear");
        model.addAttribute("ingredienteRequest", new IngredienteRequestDTO());
        return "layout";
    }

    @PostMapping("/guardar")
    public String guardarIngrediente(@ModelAttribute IngredienteRequestDTO request) {

        Ingrediente ingrediente = ingredienteMapper.toIngrediente(request);
        ingredienteService.crearIngrediente(ingrediente);
        return "redirect:/inventario";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        // Buscar ingrediente por ID
        Ingrediente ingrediente = ingredienteService.buscarPorId(id);

        // Convertir Entity a ResponseDTO para mostrar en formulario
        IngredientesResponseDTO response = ingredienteMapper.toResponseDTO(ingrediente);

        model.addAttribute("title", "Editar Ingrediente - Mr Burger");
        model.addAttribute("titulo", "✏️ Editar Ingrediente");
        model.addAttribute("content", "inventario/editar");
        model.addAttribute("ingrediente", response); // Para mostrar datos actuales
        model.addAttribute("ingredienteRequest", new IngredienteRequestDTO()); // Para enviar cambios
        return "layout";
    }

    // Procesar la actualización
    @PostMapping("/actualizar/{id}")
    public String actualizarIngrediente(@PathVariable Long id,
            @ModelAttribute IngredienteRequestDTO request) {

        // Convertir DTO a Entity
        Ingrediente ingredienteActualizado = ingredienteMapper.toIngrediente(request);

        ingredienteService.actualizar(id, ingredienteActualizado);

        return "redirect:/inventario";
    }
}