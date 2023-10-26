package be.condorcet.servicetaxi.Repositories;

import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxiRepository extends JpaRepository<Taxi, Integer> {
}
