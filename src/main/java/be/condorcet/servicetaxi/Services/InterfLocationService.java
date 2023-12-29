package be.condorcet.servicetaxi.Services;

import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Location;
import be.condorcet.servicetaxi.model.Taxi;

import java.sql.Date;
import java.util.List;

public interface InterfLocationService extends InterfService<Location>{
    public List<Location> getLocationsByClient(Client client);

    public List<Location> getLocationsByTaxi(Taxi taxi);

    public List<Location> getLocationsByDate(Date date);

}
