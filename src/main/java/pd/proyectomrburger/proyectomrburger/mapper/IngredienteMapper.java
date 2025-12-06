package pd.proyectomrburger.proyectomrburger.mapper;

import org.mapstruct.Mapper;

import pd.proyectomrburger.proyectomrburger.models.Ingrediente;
import pd.proyectomrburger.proyectomrburger.models.requestDTO.IngredienteRequestDTO;
import pd.proyectomrburger.proyectomrburger.models.responseDTO.IngredientesResponseDTO;

@Mapper(componentModel = "spring")
public interface IngredienteMapper {

    IngredientesResponseDTO toResponseDTO(Ingrediente ingrediente);
    Ingrediente toIngrediente(IngredienteRequestDTO requestDTO);

    //IngredienteRequestDTO toRequestDTO(Ingrediente ingrediente);
}
