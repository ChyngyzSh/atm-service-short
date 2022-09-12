package kg.megacom.atmserviceshort.services;

import kg.megacom.atmserviceshort.models.Account;
import kg.megacom.atmserviceshort.models.dto.AccountDto;
import kg.megacom.atmserviceshort.models.dto.requests.WithdrawRequest;
import kg.megacom.atmserviceshort.models.dto.response.WithdrawResponse;

import java.util.List;

public interface AccountService {
    AccountDto save(AccountDto accountDto);

    List<Account> findByClient(Long clintId);

    Account getFromRequest(WithdrawRequest withdrawRequest);
    WithdrawResponse withdraw(long account, int sum);
}
