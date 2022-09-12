package kg.megacom.atmserviceshort.services.impl;

import kg.megacom.atmserviceshort.dao.NominalRepo;
import kg.megacom.atmserviceshort.mappers.AccountMapper;
import kg.megacom.atmserviceshort.mappers.BalanceMapper;
import kg.megacom.atmserviceshort.mappers.NominalMapper;
import kg.megacom.atmserviceshort.models.Account;
import kg.megacom.atmserviceshort.models.Balance;
import kg.megacom.atmserviceshort.models.Nominal;
import kg.megacom.atmserviceshort.models.dto.BalanceDto;
import kg.megacom.atmserviceshort.models.dto.NominalDto;
import kg.megacom.atmserviceshort.models.dto.requests.WithdrawRequest;
import kg.megacom.atmserviceshort.models.dto.response.WithdrawResponse;
import kg.megacom.atmserviceshort.services.AccountService;
import kg.megacom.atmserviceshort.services.BalanceService;
import kg.megacom.atmserviceshort.services.NominalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<NominalDto> nominalDtoList = getAvailableNominals2(withdrawRequest.getAmount());
        int rest = balance.getBalance() - withdrawRequest.getAmount();
        if (rest >= 0) {
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



    @Override
    public List<NominalDto> getAvailableNominals2(int amount) {
        List<Nominal> nominals = nominalRepo
                .findByNominalsAndAmount(amount);
        List<Nominal> newNominals = new ArrayList<>();
        int finalAmount = (int) amount;
        while (finalAmount > nominals.get(nominals.size() - 1).getNominal()) {
            for (Nominal nominal : nominals) {
                int rest = (int) (finalAmount / nominal.getNominal());
                if (rest > 0) {
                    nominal.setId(nominal.getId());
                    nominal.setNominal(nominal.getNominal());
                    int nominalCount = (rest <= nominal.getAmount())
                            ? rest
                            : (int) nominal.getAmount();
                    nominal.setAmount(nominalCount);
                    newNominals.add(nominal);
                    finalAmount -= nominal.getNominal() * nominalCount;
                    if (finalAmount == 0 | finalAmount < nominals.get(nominals.size() - 1).getNominal()) {
                        break;
                    }
                }
            }
        }

        List<NominalDto> nominalDtos = nominalMapper.toDtos(newNominals);
        refreshNominalCount(nominalDtos);
        System.out.println("nominalDtos : " + nominalDtos);
        return nominalDtos;
    }

    @Override
    public List<NominalDto> findAllActiveNominals(int maxNominal) {
        List<Nominal> nominals = nominalRepo.findByNominalsAndAmount(maxNominal);
        return nominalMapper.toDtos(nominals);
    }

    @Override
    public void refreshNominalCount(List<NominalDto> nominalDtos) {
        for (NominalDto nominalDto:nominalDtos) {
            Nominal nominal = nominalRepo.findByNominal(nominalDto.getNominal());
            nominal.setAmount(nominal.getAmount() - nominalDto.getAmount());
            nominalRepo.save(nominal);
        }
    }
//    public void saveAfterWithdraw(List<NominalDto> nominalDtos) {
//        List<Nominal> allNominals = nominalRepo.findAll();
//        System.out.println("allNominals " + allNominals);
//        List<Nominal> withdrawNominals = nominalMapper.toEntities(nominalDtos);
//
//        for (int i = 0; i < allNominals.size(); i++) {
//            for (int j = 0; j < withdrawNominals.toArray().length; j++) {
//                if (allNominals.get(i).getNominal() == withdrawNominals.get(j).getNominal()) {
//                    Nominal nominal = new Nominal();
//                    nominal.setId(allNominals.get(i).getId());
//                    nominal.setNominal(allNominals.get(i).getNominal());
//                    nominal.setAmount(allNominals.get(i).getAmount() - withdrawNominals.get(j).getAmount());
//                    //allNominals.remove(allNominals.get(i));
//                    allNominals.add(nominal);
//                }
//            }
//        }
//        nominalRepo.saveAll(allNominals);
//    }
}

/*
@Override
    public List<NominalDto> getAvailableNominals(double amount) {
        List<Nominal> nominals = nominalRepo
                .findByNominalsAndAmount(amount);
        System.out.println("required amount: " + amount);
        System.out.println("available nominals: " + nominals);
        List<Nominal> newNominals = new ArrayList<>();
        int i=0;
        double minNominal=nominals.get(nominals.size()-1).getNominal();
        int result = (int) (amount / nominals.get(0).getNominal());
        while (amount >= minNominal){
            if(result>0){
                Nominal nominal = new Nominal();
                nominal.setId((long) i);
                nominal.setNominal(nominals.get(i).getNominal());
                int nominalCount = result <= nominals.get(i).getAmount()
                        ? result
                        : (int) nominals.get(i).getAmount();
                nominal.setAmount(nominalCount);
                newNominals.add(nominal);
                amount -= nominals.get(i).getNominal() * nominalCount;
            }
            i++;
            result= i < nominals.size()
                    ? (int) (amount / nominals.get(i).getNominal())
                    : 0;
            System.out.println("result : " + result);
        }
        List<NominalDto> nominalDtos = nominalMapper.toDtos(newNominals);
        saveAfterWithdraw(nominalDtos);
        System.out.println("nominalDtos : " + nominalDtos);
        return nominalDtos;
    }
 */

/*
finalAmount -= diff >= 0 ? (int) (nominal.getAmount() * nominal.getNominal())
                   : (int) (rest * nominal.getNominal());
 */

/*
public List<NominalDto> getAvailableNominals(double amount) {
        List<Nominal> nominals = nominalRepo
                .findByNominalsAndAmount(amount);
        System.out.println("required amount: " + amount);
        System.out.println("available nominals: " + nominals);
        List<Nominal> newNominals = new ArrayList<>();
        int i=0;
        double minNominal=nominals.get(nominals.size()-1).getNominal();
        int result = (int) (amount / nominals.get(0).getNominal());
        while (amount >= minNominal && result >=1){
                if(result<= nominals.get(i).getAmount()){
                    Nominal nominal = new Nominal();
                    nominal.setId((long) i);
                    nominal.setNominal(nominals.get(i).getNominal());
                    nominal.setAmount(result);
                    newNominals.add(nominal);
                    amount-=nominals.get(i).getNominal()*result;
                }else {
                    Nominal nominal = new Nominal();
                    nominal.setId((long) i);
                    nominal.setNominal(nominals.get(i).getNominal());
                    nominal.setAmount(result);
                    newNominals.add(nominal);
                    amount-=nominals.get(i).getNominal()*result;
                }
            i++;
            result= (int) (amount / nominals.get(i).getNominal());
        }
        List<NominalDto> nominalDtos = nominalMapper.toDtos(newNominals);
        System.out.println("nominalDtos : " + nominalDtos);
        return nominalDtos;
    }
 */
/*
public List<NominalDto> getNominals(double amount) {
        List<Nominal>nominals = nominalRepo.getAllNominals();
        List<Nominal>nomList=new ArrayList<>();

        long j=0;
        for(int i=0; i<=amount; i++){
            if(amount>0){
                int nominal = (int) nominals.get(i).getNominal();
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
            int nominal = (int) nominals.get(i).getNominal();
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
 */