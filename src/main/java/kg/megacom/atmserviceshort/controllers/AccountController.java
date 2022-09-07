package kg.megacom.atmserviceshort.controllers;

import kg.megacom.atmserviceshort.models.Account;
import kg.megacom.atmserviceshort.models.Client;
import kg.megacom.atmserviceshort.models.dto.AccountDto;
import kg.megacom.atmserviceshort.services.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("/withdrawal")
    public AccountDto witdraw(@RequestBody AccountDto accountDto){
        return accountService.save(accountDto);
    }
}
