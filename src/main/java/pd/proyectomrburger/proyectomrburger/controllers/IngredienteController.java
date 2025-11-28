package pd.proyectomrburger.proyectomrburger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import pd.proyectomrburger.proyectomrburger.models.Ingrediente;
import pd.proyectomrburger.proyectomrburger.models.requestDTO.IngredienteRequestDTO;
import pd.proyectomrburger.proyectomrburger.models.responseDTO.IngredienteResponseDTO;
import pd.proyectomrburger.proyectomrburger.services.IngredienteService;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class IngredienteController {

    //private final IngredienteService ingredienteService;
  /*  private final IngredienteMapper ingredienteMapper;

    @Autowired
    public IngredienteController(IngredienteService ingredienteService, IngredienteMapper ingredienteMapper) {
        this.ingredienteService = ingredienteService;
        this.ingredienteMapper = ingredienteMapper;
    }

    @PostMapping("/crearingrediente")
    public ResponseEntity<IngredienteResponseDTO> crearIngrediente(
            @Valid @RequestBody IngredienteRequestDTO ingredienteRequestDTO) {
        Ingrediente ingredienteCrear = ingredienteMapper.toEntity(ingredienteRequestDTO);
        System.out.println(ingredienteCrear.getActivo());
        System.out.println(ingredienteCrear.getUnidadMedida());
        System.out.println(ingredienteCrear.getNombreIngrediente());
                ingredienteService.crearIngrediente(ingredienteCrear);
        return ResponseEntity.ok().build();

    }
 */
}
