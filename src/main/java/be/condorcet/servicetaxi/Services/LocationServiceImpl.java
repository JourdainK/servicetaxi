package be.condorcet.servicetaxi.Services;

import be.condorcet.servicetaxi.Repositories.ClientRepository;
import be.condorcet.servicetaxi.Repositories.LocationRepository;
import be.condorcet.servicetaxi.Repositories.TaxiRepository;
import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Location;
import be.condorcet.servicetaxi.model.Taxi;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class LocationServiceImpl implements InterfLocationService{
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TaxiRepository taxiRepository;

    @Override
    public List<Location> getLocationsByClient(Client client) {
        //get list of location by client, with findAll and a filter
        List<Location> llocs = locationRepository.findLocationByClient(client);
        return llocs;
    }

    @Override
    public List<Location> getLocationsByTaxi(Taxi taxi) {
        List<Location> llocTaxi = locationRepository.findLocationByTaxi(taxi);
        return null;
    }

    @Override
    public List<Location> getLocationsByDate(Date date) {
        //TODO fix this which is not working with the test
        //List<Location> llocDate = locationRepository.findLocationByDate(date);
        return null;
    }

    @Override
    public Location create(Location location) throws Exception {
        locationRepository.save(location);
        return location;
    }

    @Override
    public Location read(Integer id) throws Exception {
        return locationRepository.findById(id).get();
    }

    @Override
    public Location update(Location location) throws Exception {
        read(location.getIdlocation());
        locationRepository.save(location);
        return location;
    }

    @Override
    public void delete(Location location) throws Exception {
        locationRepository.deleteById(location.getIdlocation());
    }

    @Override
    public List<Location> all() throws Exception {
        return locationRepository.findAll();
    }
}
