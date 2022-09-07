package kg.megacom.atmserviceshort.models.dto.response;

import kg.megacom.atmserviceshort.models.dto.NominalDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WithdrawResponse {
    long account;
    int amount;
    double rest;
    List<NominalDto> nominals;
}
