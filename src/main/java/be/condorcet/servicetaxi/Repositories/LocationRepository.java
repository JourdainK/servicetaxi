package be.condorcet.servicetaxi.Repositories;

import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Location;
import be.condorcet.servicetaxi.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@EnableJpaRepositories
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    public List<Location> findLocationByClient(Client cl);

    public List<Location> findLocationByTaxi(Taxi taxi);

    public List<Location> findLocationByDateloc(Date date);
}