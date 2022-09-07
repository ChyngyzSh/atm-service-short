package kg.megacom.atmserviceshort.mappers;

import kg.megacom.atmserviceshort.mappers.base.CrudMapper;
import kg.megacom.atmserviceshort.models.Client;
import kg.megacom.atmserviceshort.models.dto.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ClientMapper extends CrudMapper<Client, ClientDto> {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
}
