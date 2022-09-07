package kg.megacom.atmserviceshort.dao;

import kg.megacom.atmserviceshort.models.Nominal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NominalRepo extends JpaRepository<Nominal, Long> {

    @Query("select u from Nominal u order by u.nominal desc ")
    List<Nominal>getAllNominals();
}
