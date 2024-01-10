package be.condorcet.servicetaxi.Services;

import be.condorcet.servicetaxi.Repositories.ClientRepository;
import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Location;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class ClientServiceImpl implements InterfClientService {

    @Autowired ClientRepository clientRepository;

    @Override
    public List<Client> findByNomcli(String nom) {
        return clientRepository.findByNomcli(nom);
    }

    @Override
    public List<Client> findClientByNomcliLike(String nom) {
        return clientRepository.findClientByNomcliLike(nom);
    }

    @Override
    public Client read(String nom, String prenom, String mail) {
        return clientRepository.findByNomcliAndPrenomcliAndMail(nom, prenom, mail);
    }

    @Override
    public Client create(Client client) throws Exception {
        clientRepository.save(client);
        return client;
    }

    @Override
    public Client read(Integer id) throws Exception {
        Optional<Client> opClient = clientRepository.findById(id);
        return opClient.get();
    }

    @Override
    public Client update(Client client) throws Exception {
        read(client.getIdclient());
        clientRepository.save(client);
        return client;
    }

    @Override
    public void delete(Client client) throws Exception {
        clientRepository.deleteById(client.getIdclient());
    }

    @Override
    public List<Client> all() throws Exception {
        return clientRepository.findAll();
    }

}
