package kg.megacom.atmserviceshort.services.impl;

import kg.megacom.atmserviceshort.dao.AccountRepo;
import kg.megacom.atmserviceshort.mappers.AccountMapper;
import kg.megacom.atmserviceshort.models.Account;
import kg.megacom.atmserviceshort.models.Balance;
import kg.megacom.atmserviceshort.models.dto.AccountDto;
import kg.megacom.atmserviceshort.models.dto.NominalDto;
import kg.megacom.atmserviceshort.models.dto.requests.WithdrawRequest;
import kg.megacom.atmserviceshort.models.dto.response.WithdrawResponse;
import kg.megacom.atmserviceshort.services.AccountService;
import kg.megacom.atmserviceshort.services.BalanceService;
import kg.megacom.atmserviceshort.services.NominalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private NominalService nominalService;

    @Autowired
    private BalanceService balanceService;

    private final AccountMapper accountMapper;

    public AccountServiceImpl() {
        this.accountMapper = AccountMapper.INSTANCE;
    }

    @Override
    public AccountDto save(AccountDto accountDto) {
        Account account = accountMapper.toEntity(accountDto);
        account=accountRepo.save(account);

        return accountMapper.toDto(account);
    }

    @Override
    public List<Account> findByClient(Long clientId) {
        return accountRepo.findAllByClientId(clientId);
    }

    @Override
    public Account getFromRequest(WithdrawRequest withdrawRequest) {
        Account account = accountRepo.findByAccount(withdrawRequest.getAccount());
        return account;
    }

    @Override
    public WithdrawResponse withdraw(long account, int sum) {

        List<NominalDto> clientNominals = new ArrayList<>();

        Account clientAccount = accountRepo.findByAccount(account);
        int finalAmount = sum;

        if (clientAccount.getBalance().getBalance() < sum)
            throw new RuntimeException("Недостаточно средств!");

        List<NominalDto> nominalDtos = nominalService.findAllActiveNominals(sum);

        for (NominalDto item : nominalDtos) {
            int rest = finalAmount / item.getNominal();
            int diff = rest - item.getAmount(); // хватает ли купюр
            if (rest > 0) {
                finalAmount -= diff >= 0 ? item.getAmount() * item.getNominal() : rest * item.getNominal();

                item.setAmount(diff >= 0 ? item.getAmount() : rest);
                clientNominals.add(item);
            }

            if (finalAmount == 0)
                break;
        }

        if (finalAmount != 0) {
            throw new RuntimeException("Некорректная сумма!");
        }

        Balance balance = clientAccount.getBalance();
        balance.setBalance(balance.getBalance() - sum);
        balanceService.save(balance);

        nominalService.refreshNominalCount(clientNominals);

        WithdrawResponse response = new WithdrawResponse();
        response.setAccount(account);
        response.setAmount(sum);
        response.setNominals(clientNominals);
        response.setRest(clientAccount.getBalance().getBalance() - sum);

        return response;
    }
}
