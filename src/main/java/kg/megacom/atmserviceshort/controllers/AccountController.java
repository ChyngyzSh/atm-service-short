package kg.megacom.atmserviceshort.controllers;

import kg.megacom.atmserviceshort.models.Account;
import kg.megacom.atmserviceshort.models.Client;
import kg.megacom.atmserviceshort.models.dto.AccountDto;
import kg.megacom.atmserviceshort.models.dto.response.WithdrawResponse;
import kg.megacom.atmserviceshort.services.AccountService;
import org.aspectj.weaver.tools.ISupportsMessageContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/save")
    public AccountDto save(@RequestBody AccountDto accountDto){
        return accountService.save(accountDto);
    }

    @GetMapping("/withdraw")
    public WithdrawResponse withdraw(@RequestParam long account, @RequestParam int sum){
        return accountService.withdraw(account, sum);
    }

}
