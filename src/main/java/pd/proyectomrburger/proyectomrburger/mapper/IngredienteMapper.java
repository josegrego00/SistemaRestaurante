package pd.proyectomrburger.proyectomrburger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pd.proyectomrburger.proyectomrburger.models.Ingrediente;
import pd.proyectomrburger.proyectomrburger.models.requestDTO.IngredienteRequestDTO;
import pd.proyectomrburger.proyectomrburger.models.responseDTO.IngredienteResponseDTO;

@Mapper(componentModel = "spring")
public interface IngredienteMapper {

    IngredienteResponseDTO toResponseDTO(Ingrediente ingrediente);

    @Mapping(target = "activo", source = "activo")
    @Mapping(target = "stockMinimo", source = "stockMinimo")
    Ingrediente toEntity(IngredienteRequestDTO ingredienteRequestDTO);

}
