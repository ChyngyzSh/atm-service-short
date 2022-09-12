package kg.megacom.atmserviceshort.services;

import kg.megacom.atmserviceshort.models.Nominal;
import kg.megacom.atmserviceshort.models.dto.NominalDto;
import kg.megacom.atmserviceshort.models.dto.requests.WithdrawRequest;
import kg.megacom.atmserviceshort.models.dto.response.WithdrawResponse;

import java.util.List;

public interface NominalService {
    NominalDto save(NominalDto nominalDto);

    WithdrawResponse withdrawMoney(WithdrawRequest withdrawRequest);

    List<NominalDto> getAvailableNominals2(double amount);

}
/*
List<NominalDto> getNominals(double amount);
    List<NominalDto> getNominals2(double amount);
    List<NominalDto> getAvailableNominals(double amount);
 */