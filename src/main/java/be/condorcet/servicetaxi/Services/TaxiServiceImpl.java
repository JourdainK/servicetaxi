package be.condorcet.servicetaxi.Services;


import be.condorcet.servicetaxi.Repositories.LocationRepository;
import be.condorcet.servicetaxi.Repositories.TaxiRepository;
import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Location;
import be.condorcet.servicetaxi.model.Taxi;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class TaxiServiceImpl implements InterfTaxiService{

    @Autowired TaxiRepository taxiRepository;
    @Autowired LocationRepository locationRepository;

    @Override
    public Taxi create(Taxi taxi) throws Exception {
        taxiRepository.save(taxi);
        return taxi;
    }

    @Override
    public Taxi read(Integer id) throws Exception {
        return taxiRepository.findById(id).get();
    }

    @Override
    public Taxi update(Taxi taxi) throws Exception {
        read(taxi.getIdtaxi());
        taxiRepository.save(taxi);
        return taxi;
    }

    @Override
    public void delete(Taxi taxi) throws Exception {
        taxiRepository.deleteById(taxi.getIdtaxi());
    }

    @Override
    public List<Taxi> all() throws Exception {
        return taxiRepository.findAll();
    }

    @Override
    public List<Location> getLocationsByTaxi(Taxi taxi) {
        List<Location> llocTaxi = locationRepository.findLocationByTaxi(taxi);
        return llocTaxi;
    }
}
