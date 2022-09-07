package kg.megacom.atmserviceshort.controllers;

import kg.megacom.atmserviceshort.models.Client;
import kg.megacom.atmserviceshort.models.dto.ClientDto;
import kg.megacom.atmserviceshort.models.dto.ClientInfoDto;
import kg.megacom.atmserviceshort.services.ClientService;
import kg.megacom.atmserviceshort.services.impl.ClientServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @PostMapping("/save")
    public ClientDto save(@RequestBody ClientDto clientDto){
        return clientService.save(clientDto);
    }

    @GetMapping("/info")
    public ClientInfoDto getInfo(@RequestParam Long clintId){
        return clientService.getInfo(clintId);
    }

}
