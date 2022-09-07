package kg.megacom.atmserviceshort.services;

import kg.megacom.atmserviceshort.models.Balance;
import kg.megacom.atmserviceshort.models.dto.BalanceDto;

public interface BalanceService {
    BalanceDto save(BalanceDto balanceDto);
}
