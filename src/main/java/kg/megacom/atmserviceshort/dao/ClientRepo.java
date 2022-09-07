package kg.megacom.atmserviceshort.dao;

import kg.megacom.atmserviceshort.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {

}
