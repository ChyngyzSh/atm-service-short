package kg.megacom.atmserviceshort.services;

import kg.megacom.atmserviceshort.models.Client;
import kg.megacom.atmserviceshort.models.dto.ClientDto;
import kg.megacom.atmserviceshort.models.dto.ClientInfoDto;

public interface ClientService {
    ClientDto save(ClientDto clientDto);

    ClientInfoDto getInfo(Long clintId);
}
