package kg.megacom.atmserviceshort.services.impl;

import kg.megacom.atmserviceshort.dao.AccountRepo;
import kg.megacom.atmserviceshort.mappers.AccountMapper;
import kg.megacom.atmserviceshort.models.Account;
import kg.megacom.atmserviceshort.models.dto.AccountDto;
import kg.megacom.atmserviceshort.models.dto.requests.WithdrawRequest;
import kg.megacom.atmserviceshort.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepo accountRepo;

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
    public List<Account> findByClient(Long clintId) {
        return accountRepo.findAllByClientId(clintId);
    }

    @Override
    public Account getFromRequest(WithdrawRequest withdrawRequest) {
        Account account = accountRepo.findByAccount(withdrawRequest.getAccount());
        return account;
    }


}
