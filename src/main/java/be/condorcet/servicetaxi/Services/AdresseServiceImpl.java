package be.condorcet.servicetaxi.Services;

import be.condorcet.servicetaxi.Repositories.AdresseRepository;
import be.condorcet.servicetaxi.model.Adresse;
import be.condorcet.servicetaxi.model.Location;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class AdresseServiceImpl implements InterfAdresseService{
    @Autowired
    private AdresseRepository adresseRepository;
    @Autowired
    private LocationServiceImpl locationService;

    @Override
    public Adresse create(Adresse adresse) throws Exception {
        adresseRepository.save(adresse);
        return adresse;
    }

    @Override
    public Adresse read(Integer id) throws Exception {
        return adresseRepository.findById(id).get();
    }

    @Override
    public Adresse update(Adresse adresse) throws Exception {
        read(adresse.getIdadresse());
        adresseRepository.save(adresse);
        return adresse;
    }

    @Override
    public void delete(Adresse adresse) throws Exception {
        adresseRepository.deleteById(adresse.getIdadresse());
    }

    @Override
    public List<Adresse> all() throws Exception {
        return adresseRepository.findAll();
    }

}
