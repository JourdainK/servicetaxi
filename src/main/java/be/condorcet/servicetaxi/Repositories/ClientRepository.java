package be.condorcet.servicetaxi.Repositories;

import be.condorcet.servicetaxi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@EnableJpaRepositories
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

   List<Client> findByNomcli(String nomcli);

   Client findByNomcliAndPrenomcliAndMail(String nom, String prenom, String mail);

}
