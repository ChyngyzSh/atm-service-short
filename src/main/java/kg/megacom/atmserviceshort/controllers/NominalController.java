package kg.megacom.atmserviceshort.controllers;

import kg.megacom.atmserviceshort.models.Nominal;
import kg.megacom.atmserviceshort.models.dto.NominalDto;
import kg.megacom.atmserviceshort.models.dto.requests.WithdrawRequest;
import kg.megacom.atmserviceshort.models.dto.response.WithdrawResponse;
import kg.megacom.atmserviceshort.services.NominalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/nominal")
public class NominalController {

    private final NominalService nominalService;

    public NominalController(NominalService nominalService) {
        this.nominalService = nominalService;
    }


    @PostMapping("/save")
    public NominalDto save(@RequestBody NominalDto nominalDto){
        return nominalService.save(nominalDto);
    }


    @GetMapping("/withdraw")
    public WithdrawResponse withdrawMoney(@RequestBody WithdrawRequest withdrawRequest){
        return nominalService.withdrawMoney(withdrawRequest);
    }
}
