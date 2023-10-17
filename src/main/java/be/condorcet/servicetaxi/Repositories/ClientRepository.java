package be.condorcet.servicetaxi.Repositories;

import be.condorcet.servicetaxi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    //doesn't work at all, no error message, just bad request page .
    //public List<Client> findBynomcli(String nomcli);
    /*
    @Query(value = "SELECT cl FROM Client cl WHERE cl.nomcli = :nomcli")
    Collection<Client> findClientByName(@Param("nomcli") String nomcli);

     */


   Collection<Client> findClientsByNomcli(String nomcli);

}
