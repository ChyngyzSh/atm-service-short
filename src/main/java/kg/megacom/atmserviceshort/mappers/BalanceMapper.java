package kg.megacom.atmserviceshort.mappers;

import kg.megacom.atmserviceshort.mappers.base.CrudMapper;
import kg.megacom.atmserviceshort.models.Balance;
import kg.megacom.atmserviceshort.models.dto.BalanceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BalanceMapper extends CrudMapper<Balance, BalanceDto> {

    BalanceMapper INSTANCE = Mappers.getMapper(BalanceMapper.class);

}
