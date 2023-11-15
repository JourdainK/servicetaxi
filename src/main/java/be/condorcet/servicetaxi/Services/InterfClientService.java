package be.condorcet.servicetaxi.Services;

import be.condorcet.servicetaxi.model.Client;

import java.util.List;

public interface InterfClientService extends InterfService<Client> {
    public List<Client> read(String nom);

    Client read(String nom, String prenom, String mail);
}