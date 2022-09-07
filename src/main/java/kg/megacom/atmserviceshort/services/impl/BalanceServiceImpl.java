package kg.megacom.atmserviceshort.services.impl;

import kg.megacom.atmserviceshort.dao.BalanceRepo;
import kg.megacom.atmserviceshort.mappers.BalanceMapper;
import kg.megacom.atmserviceshort.models.Balance;
import kg.megacom.atmserviceshort.models.dto.BalanceDto;
import kg.megacom.atmserviceshort.services.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {
    @Autowired
    private BalanceRepo balanceRepo;

    private final BalanceMapper balanceMapper;

    public BalanceServiceImpl() {
        this.balanceMapper = BalanceMapper.INSTANCE;
    }

    @Override
    public BalanceDto save(BalanceDto balanceDto) {
        Balance balance = balanceMapper.toEntity(balanceDto);
        balance=balanceRepo.save(balance);
        return balanceMapper.toDto(balance);
    }
}
