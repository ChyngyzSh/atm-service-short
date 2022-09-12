package kg.megacom.atmserviceshort.services.impl;

import kg.megacom.atmserviceshort.dao.ClientRepo;
import kg.megacom.atmserviceshort.mappers.AccountMapper;
import kg.megacom.atmserviceshort.mappers.ClientMapper;
import kg.megacom.atmserviceshort.models.Account;
import kg.megacom.atmserviceshort.models.Client;
import kg.megacom.atmserviceshort.models.dto.AccountDto;
import kg.megacom.atmserviceshort.models.dto.ClientDto;
import kg.megacom.atmserviceshort.models.dto.ClientInfoDto;
import kg.megacom.atmserviceshort.services.AccountService;
import kg.megacom.atmserviceshort.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private AccountService accountService;

    private final ClientMapper clientMapper;
    private final AccountMapper accountMapper;

    public ClientServiceImpl() {
        this.accountMapper = AccountMapper.INSTANCE;
        this.clientMapper = ClientMapper.INSTANCE;
    }


    @Override
    public ClientDto save(ClientDto clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        client=clientRepo.save(client);
        return clientMapper.toDto(client);
    }

    @Override
    public ClientInfoDto getInfo(Long clintId) {
        Client client=clientRepo.findById(clintId).orElseThrow();
        List<Account> accounts = accountService.findByClient(clintId);

        ClientDto clientDto = clientMapper.toDto(client);
        List<AccountDto>accountDtos = accountMapper.toDtos(accounts);
        ClientInfoDto clientInfoDto=new ClientInfoDto();
        clientInfoDto.setClient(clientDto);
        clientInfoDto.setAccountList(accountDtos);
        return clientInfoDto;
    }
}
