package kg.megacom.atmserviceshort.mappers;

import kg.megacom.atmserviceshort.mappers.base.CrudMapper;
import kg.megacom.atmserviceshort.models.Account;
import kg.megacom.atmserviceshort.models.Balance;
import kg.megacom.atmserviceshort.models.dto.AccountDto;
import kg.megacom.atmserviceshort.models.dto.BalanceDto;
import kg.megacom.atmserviceshort.models.dto.ClientInfoDto;
import kg.megacom.atmserviceshort.models.dto.requests.WithdrawRequest;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper{

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @InheritInverseConfiguration
    List<Account> toEntities(List<AccountDto>accountDtos);
    @Mapping(source = "balance.balance", target = "balance")
    List<AccountDto> toDtos(List<Account>accounts);

    @Mapping(source = "balance.balance", target = "balance")
    AccountDto toDto(Account account);
    @InheritInverseConfiguration
    Account toEntity(AccountDto accountDto);

    Account withdrawRequestToAccount(WithdrawRequest withdrawRequest);
}
//    @Mapping(source = "name", target = "title")
//    Account toEntity(AccountDto accountDto);
