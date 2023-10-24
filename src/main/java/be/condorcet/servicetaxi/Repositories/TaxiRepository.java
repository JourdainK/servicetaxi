package be.condorcet.servicetaxi.Repositories;

import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxiRepository extends JpaRepository<Taxi, Integer> {
    //TODO ,check what methods has to be here
    //public List<Taxi> findTaxiByClient(Client cl);
}
