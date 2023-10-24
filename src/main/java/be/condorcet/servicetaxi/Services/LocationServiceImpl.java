package be.condorcet.servicetaxi.Services;

import be.condorcet.servicetaxi.Repositories.ClientRepository;
import be.condorcet.servicetaxi.Repositories.LocationRepository;
import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Location;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements InterfLocationService{
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Location> getLocations(Client client) {
        List<Location> llocs = locationRepository.findAll();
        return llocs;
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
