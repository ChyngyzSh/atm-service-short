package kg.megacom.atmserviceshort.controllers;

import kg.megacom.atmserviceshort.models.Nominal;
import kg.megacom.atmserviceshort.models.dto.NominalDto;
import kg.megacom.atmserviceshort.models.dto.requests.WithdrawRequest;
import kg.megacom.atmserviceshort.models.dto.response.WithdrawResponse;
import kg.megacom.atmserviceshort.services.NominalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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



    @GetMapping("/get2")
    public List<NominalDto> getAvailableNominals2(double amount){
        return nominalService.getAvailableNominals2(amount);
    }
}
/*
@GetMapping("/get")
    public List<NominalDto> getAvailableNominals(double amount){
        return nominalService.getAvailableNominals(amount);
    }
 */