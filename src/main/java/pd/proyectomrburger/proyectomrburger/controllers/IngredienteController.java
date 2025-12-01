package pd.proyectomrburger.proyectomrburger.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pd.proyectomrburger.proyectomrburger.mapper.IngredienteMapper;
import pd.proyectomrburger.proyectomrburger.models.Ingrediente;
import pd.proyectomrburger.proyectomrburger.models.requestDTO.IngredienteRequestDTO;
import pd.proyectomrburger.proyectomrburger.models.responseDTO.IngredientesResponseDTO;
import pd.proyectomrburger.proyectomrburger.services.IngredienteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/restaurante")
@AllArgsConstructor

public class IngredienteController {

    private final IngredienteService ingredienteService;
    private final IngredienteMapper ingredienteMapper;

    @PostMapping("/crearIngrediente")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<IngredientesResponseDTO> crearIngrediente(@RequestBody IngredienteRequestDTO requestDTO) {

        Ingrediente ingrediente = ingredienteMapper.toIngrediente(requestDTO);
        Ingrediente ingredienteGuardar = ingredienteService.crearIngrediente(ingrediente);
        IngredientesResponseDTO responseDTO = ingredienteMapper.toResponseDTO(ingredienteGuardar);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    @GetMapping("/ingredientes")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<IngredientesResponseDTO>> listarIngredientes() {
        List<Ingrediente> ingredientes = ingredienteService.listarTodos();
        List<IngredientesResponseDTO> response = ingredientes.stream()
                .map(ingredienteMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ingredientes/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<IngredientesResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Ingrediente> ingredienteOptional = ingredienteService.buscarPorId(id);
        Ingrediente ingrediente = ingredienteOptional.get();
        IngredientesResponseDTO response = ingredienteMapper.toResponseDTO(ingrediente);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ingredientes/unidad/{unidadMedida}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<IngredientesResponseDTO>> buscarPorUnidad(
            @PathVariable String unidadMedida) {
        List<Ingrediente> ingredientes = ingredienteService.buscarPorUnidadMedida(unidadMedida);
        List<IngredientesResponseDTO> response = ingredientes.stream()
                .map(ingredienteMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/ingredientes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<IngredientesResponseDTO> actualizarIngrediente(
            @PathVariable Long id,
            @Valid @RequestBody IngredienteRequestDTO requestDTO) {

        Ingrediente ingredienteActualizado = ingredienteService.actualizar(id, requestDTO);
        IngredientesResponseDTO response = ingredienteMapper.toResponseDTO(ingredienteActualizado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/ingredientes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> desactivarIngrediente(@PathVariable Long id) {
        ingredienteService.desactivar(id);
        return ResponseEntity.noContent().build();
    }

}
