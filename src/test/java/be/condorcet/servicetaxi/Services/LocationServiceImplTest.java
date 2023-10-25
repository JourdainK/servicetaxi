package be.condorcet.servicetaxi.Services;

import be.condorcet.servicetaxi.Repositories.AdresseRepository;
import be.condorcet.servicetaxi.model.Adresse;
import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Location;
import be.condorcet.servicetaxi.model.Taxi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationServiceImplTest {

    private Client client;
    private Location location;
    private Taxi taxi;
    private Adresse adrDepart;
    private Adresse adrArrivee;

    @Autowired
    private ClientServiceImpl clientServiceImpl;
    @Autowired
    private LocationServiceImpl locationServiceImpl;
    @Autowired
    private TaxiServiceImpl taxiServiceImpl;
    @Autowired
    private AdresseServiceImpl adresseServiceImpl;
    //TODO adresse, or can't do a single test ...

    @BeforeEach
    void setUp() {
        //mail and id UNIQUE !
        try{
            client = new Client("testmail@test.com","NameTest", "SurnameTest","065123123");
            clientServiceImpl.create(client);
            System.out.println("client is created : " + client);
            taxi = new Taxi(null,"T-XXX-001",2,new BigDecimal(1.99), new ArrayList<>());
            taxiServiceImpl.create(taxi);
            System.out.println("taxi is created : " + taxi);
            adrDepart = new Adresse(null,1000,"Bruxelles","Rue Test","Belgique");
            adresseServiceImpl.create(adrDepart);
            adrArrivee = new Adresse(null,1000,"Bruxelles","Rue Test2","Belgique");
            adresseServiceImpl.create(adrArrivee);
            //total location will be calculated by the database (trigger : INSERT_PRIXTOT_TRIGGER)
            location = new Location(null,Date.valueOf(LocalDate.now()),10,2,null,taxi,client,adrDepart,adrArrivee);
            locationServiceImpl.create(location);
            System.out.println("location created : " + location);
        } catch (Exception e){
            System.out.println("error while creating the location "+e);
        }
    }

    @AfterEach
    void tearDown() {

        try {
            locationServiceImpl.delete(location);
        }catch (Exception e){
            System.out.println("Error while deleting the location "+e);
        }

        try{
            clientServiceImpl.delete(client);
        }catch(Exception e){
            System.out.println("Error while deleting the client "+e);
        }

        try {
            taxiServiceImpl.delete(taxi);
        }catch (Exception e){
            System.out.println("Error while deleting the taxi "+e);
        }
        try {
            adresseServiceImpl.delete(adrDepart);
        }catch (Exception e){
            System.out.println("Error while deleting the starting adresse "+e);
        }

        try {
            adresseServiceImpl.delete(adrArrivee);
        }catch (Exception e){
            System.out.println("Error while deleting the arrival adresse "+e);
        }
    }

    @Test
    void getLocationsByClient() {
    }

    @Test
    void getLocationsByTaxi() {
    }

    @Test
    void getLocationsByDate() {
    }

    @Test
    void create() {
        assertNotEquals(0, location.getIdlocation(), "location id not incremented");
    }

    @Test
    void read() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void all() {
    }
}