package kg.megacom.atmserviceshort.dao;

import kg.megacom.atmserviceshort.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepo extends JpaRepository<Balance, Long> {

}
