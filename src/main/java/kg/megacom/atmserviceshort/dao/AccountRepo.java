package kg.megacom.atmserviceshort.dao;

import kg.megacom.atmserviceshort.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long>{
    List<Account>findAllByClientId(Long id);

    Account findByAccount(long account);
}
