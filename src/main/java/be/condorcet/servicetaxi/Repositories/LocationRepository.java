package be.condorcet.servicetaxi.Repositories;

import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    public List<Location> findLocationByClient(Client cl);

    public List<Location> findLocationByDateloc(Date dateloc);
}
