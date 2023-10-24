package be.condorcet.servicetaxi.Services;

import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Taxi;

import java.util.List;

public interface InterfTaxiService extends InterfService<Taxi> {
    public List<Taxi> getTaxis(Client client);
}
