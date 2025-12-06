package pd.proyectomrburger.proyectomrburger.mapper;

import org.mapstruct.Mapper;

import pd.proyectomrburger.proyectomrburger.models.Receta;
import pd.proyectomrburger.proyectomrburger.models.requestDTO.RecetaRequestDTO;
import pd.proyectomrburger.proyectomrburger.models.responseDTO.RecetaResponseDTO;

@Mapper(componentModel = "spring")
public interface RecetaMapper {

    Receta toEntity(RecetaRequestDTO request);
    RecetaResponseDTO toResponseDTO(Receta receta);

}
