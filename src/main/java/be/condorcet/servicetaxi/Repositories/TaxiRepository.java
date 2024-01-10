package be.condorcet.servicetaxi.Repositories;

import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Location;
import be.condorcet.servicetaxi.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@EnableJpaRepositories
@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Integer> {

    @Query("SELECT l FROM Location l WHERE l.taxi = :taxi AND l.dateloc BETWEEN :date1 AND :date2")
    List<Location> findLocationBetween(@Param("taxi") Taxi taxi, @Param("date1") Date date1, @Param("date2") Date date2);

}
