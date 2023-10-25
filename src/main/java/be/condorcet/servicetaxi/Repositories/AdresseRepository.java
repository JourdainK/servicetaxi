package be.condorcet.servicetaxi.Repositories;

import be.condorcet.servicetaxi.model.Adresse;
import be.condorcet.servicetaxi.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Integer> {

}
