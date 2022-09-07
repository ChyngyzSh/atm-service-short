package kg.megacom.atmserviceshort.controllers;

import kg.megacom.atmserviceshort.models.Balance;
import kg.megacom.atmserviceshort.models.dto.BalanceDto;
import kg.megacom.atmserviceshort.services.BalanceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/balance")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }


    @PostMapping("/save")
    public BalanceDto save(@RequestBody BalanceDto balanceDto){
        return balanceService.save(balanceDto);
    }
}
