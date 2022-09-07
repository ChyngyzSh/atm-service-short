package kg.megacom.atmserviceshort.mappers;

import kg.megacom.atmserviceshort.mappers.base.CrudMapper;
import kg.megacom.atmserviceshort.models.Nominal;
import kg.megacom.atmserviceshort.models.dto.NominalDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NominalMapper extends CrudMapper<Nominal, NominalDto> {
    NominalMapper INSTANCE = Mappers.getMapper(NominalMapper.class);
}
