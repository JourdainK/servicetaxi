package be.condorcet.servicetaxi.Services;

import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Location;

import java.util.List;

public interface InterfLocationService extends InterfService<Location>{
    public List<Location> getLocations(Client client);
}
