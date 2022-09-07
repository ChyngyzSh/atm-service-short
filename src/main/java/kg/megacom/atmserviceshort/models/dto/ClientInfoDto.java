package kg.megacom.atmserviceshort.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientInfoDto {
    @JsonIgnore
    Long id;
    ClientDto client;
    List<AccountDto> accountList;

}
