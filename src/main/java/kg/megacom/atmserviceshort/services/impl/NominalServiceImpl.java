package kg.megacom.atmserviceshort.services.impl;

import kg.megacom.atmserviceshort.dao.NominalRepo;
import kg.megacom.atmserviceshort.mappers.AccountMapper;
import kg.megacom.atmserviceshort.mappers.BalanceMapper;
import kg.megacom.atmserviceshort.mappers.NominalMapper;
import kg.megacom.atmserviceshort.models.Account;
import kg.megacom.atmserviceshort.models.Balance;
import kg.megacom.atmserviceshort.models.Nominal;
import kg.megacom.atmserviceshort.models.dto.AccountDto;
import kg.megacom.atmserviceshort.models.dto.BalanceDto;
import kg.megacom.atmserviceshort.models.dto.NominalDto;
import kg.megacom.atmserviceshort.models.dto.requests.WithdrawRequest;
import kg.megacom.atmserviceshort.models.dto.response.WithdrawResponse;
import kg.megacom.atmserviceshort.services.AccountService;
import kg.megacom.atmserviceshort.services.BalanceService;
import kg.megacom.atmserviceshort.services.NominalService;
import org.hibernate.sql.ordering.antlr.OrderingSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NominalServiceImpl implements NominalService {
    @Autowired
    private NominalRepo nominalRepo;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BalanceService balanceService;
    private final NominalMapper nominalMapper;
    private final AccountMapper accountMapper;
    private final BalanceMapper balanceMapper;

    public NominalServiceImpl() {
        this.balanceMapper = BalanceMapper.INSTANCE;
        this.accountMapper = AccountMapper.INSTANCE;
        this.nominalMapper = NominalMapper.INSTANCE;
    }


    @Override
    public NominalDto save(NominalDto nominalDto) {
        Nominal nominal = nominalMapper.toEntity(nominalDto);
        nominal = nominalRepo.save(nominal);
        return nominalMapper.toDto(nominal);
    }

    @Override
    public WithdrawResponse withdrawMoney(WithdrawRequest withdrawRequest) {
        Account account = accountService.getFromRequest(withdrawRequest);
        Balance balance = account.getBalance();
        List<NominalDto>nominalDtoList = getNominals(withdrawRequest.getAmount());

        double rest = balance.getBalance() - withdrawRequest.getAmount();
        if(rest>=0 ){
            balance.setId(balance.getId());
            balance.setBalance(rest);
            BalanceDto balanceDto = balanceService.save(balanceMapper.toDto(balance));

            WithdrawResponse withdrawResponse = new WithdrawResponse();
            withdrawResponse.setAccount(account.getAccount());
            withdrawResponse.setAmount(withdrawRequest.getAmount());
            withdrawResponse.setRest(rest);
            withdrawResponse.setNominals(nominalDtoList);

            return withdrawResponse;
        }
        return null;
    }

    public List<NominalDto> getNominals(double amount) {
        List<Nominal>nominals = nominalRepo.getAllNominals();
        List<Nominal>nomList=new ArrayList<>();

        long j=0;
        for(int i=0; i<=amount; i++){
            if(amount>0){
                int nominal = nominals.get(i).getNominal();
                int result= (int) (amount/nominal);
                if(result>0){
                    Nominal nom = new Nominal();
                    nom.setId(j);
                    nom.setNominal(nominal);
                    nom.setAmount(result);
                    amount=amount-(result*nominal);
                    nomList.add(nom);
                    j++;
                }
            }
        }
        System.out.println("nomList: " + nomList);
        List<NominalDto>nominalDtos = nominalMapper.toDtos(nomList);
        return nominalDtos;
    }


    public List<NominalDto> getNominals2(double amount) {
        List<Nominal>nominals = nominalRepo.getAllNominals();
        List<Nominal>nomList=new ArrayList<>();

        int i=0;
        long j=0;

        while (amount>0){
            int nominal = nominals.get(i).getNominal();
            i++;
            int result= (int) (amount/nominal);
            if(result>0){
                Nominal nom = new Nominal();
                nom.setId(j);
                nom.setNominal(nominal);
                nom.setAmount(result);
                nomList.add(nom);
                j++;
                amount=amount-(result*nominal);
            }
        }
        System.out.println("nom2: " + nomList);
        List<NominalDto>nominalDtos = nominalMapper.toDtos(nomList);
        return nominalDtos;
    }
}
