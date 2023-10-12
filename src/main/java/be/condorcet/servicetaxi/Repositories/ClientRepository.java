package be.condorcet.servicetaxi.Repositories;

import be.condorcet.servicetaxi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    public List<Client> findByNomcli(String nomcli);

}
