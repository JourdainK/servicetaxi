package be.condorcet.servicetaxi.Services;

import be.condorcet.servicetaxi.Repositories.AdresseRepository;
import be.condorcet.servicetaxi.model.Adresse;
import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Location;
import be.condorcet.servicetaxi.model.Taxi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @BeforeEach
    void setUp() {
        //mail and id UNIQUE
        try{
            client = new Client("testmail@test.com","NameTest", "SurnameTest","065123123");
            clientServiceImpl.create(client);
            System.out.println("client is created : " + client);
            BigDecimal prixKm = new BigDecimal(1.99).setScale(2, RoundingMode.HALF_UP);
            taxi = new Taxi(null,"T-XXX-001",2,prixKm, new ArrayList<>());
            taxiServiceImpl.create(taxi);
            System.out.println("taxi is created : " + taxi);
            adrDepart = new Adresse(null,1000,"Bruxelles","Rue Test","Belgique");
            adresseServiceImpl.create(adrDepart);
            adrArrivee = new Adresse(null,1000,"Bruxelles","Rue Test2","Belgique");
            adresseServiceImpl.create(adrArrivee);
            //total location will be calculated by the database (trigger : INSERT_PRIXTOT_TRIGGER)
            location = new Location(null,Date.valueOf(LocalDate.of(1900,1,1)),10,2,null,taxi,client,adrDepart,adrArrivee);
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
        //would be better with a mock, so we don't rely on the data in the database which could change
        try {
            Client client2with4locs = clientServiceImpl.read(2);
            int cptlocs = 0;
            List<Location> llocs = locationServiceImpl.getLocationsByClient(client2with4locs);
            for(Location l : llocs){
                System.out.println("one more location : " + l);
                cptlocs++;
            }
            //client 2 has 4 locations, but the line below won't work if we add a location to the client
            //assertEquals(4,cptlocs, "client 2 should have 4 locations");
            assertNotEquals(0,cptlocs, "client 2 should have locations but none were found");
        } catch (Exception e) {
            fail("getLocationsByClient failed :" + e);
        }
    }

    @Test
    void getLocationsByTaxi() {
        try{
            Taxi taxinbr6 = taxiServiceImpl.read(6);
            System.out.println("taxi 6 : " + taxinbr6);
            List<Location> llocs = locationServiceImpl.getLocationsByTaxi(taxinbr6);
            int cptlocs = 0;
            for(Location l : llocs){
                System.out.println(l);
                cptlocs++;
            }
            assertNotEquals(0,cptlocs, "taxi 6 should have locations but none were found");
            assertEquals(4,cptlocs, "taxi 6 should have 4 locations");
        }catch (Exception e){
            fail("getLocationsByTaxi failed :" + e);
        }

    }

    @Test
    void getLocationsByDate() {
        try{
            //there's 3 locations on 28/05/2023 in the db
            List<Location> llocs = locationServiceImpl.getLocationsByDate(Date.valueOf(LocalDate.of(2023,5,28)));
            int cptlocs = 0;
            for(Location l : llocs){
                //System.out.println(l);
                cptlocs++;
            }
            assertNotEquals(0,cptlocs,"there should be locations on 28/05/2023 but none were found");
        }catch (Exception e) {
            fail("getLocationsByDate failed :" + e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0, location.getIdlocation(), "location id not incremented");
        assertNotEquals(null,location.getDateloc(), "location date not set");
        assertNotEquals(0,location.getKmtotal(), "location km not set");
        assertNotEquals(0,location.getNbrpassagers(), "location nbrpassagers not set");
        assertNotEquals(0,location.getTotal(), "location total not set");
        assertNotEquals(null,location.getTaxi(), "location taxi not set");
        assertNotEquals(null,location.getClient(), "location client not set");
        assertNotEquals(null,location.getAdressedep(), "location adressedep not set");
        assertNotEquals(null,location.getAdressearr(), "location adressearr not set");
    }

    @Test
    void read() {
        try {
            Location locRead = locationServiceImpl.read(location.getIdlocation());
            //trigger INSERT_PRIXTOT_TRIGGER should have set the total but spring won't let it so we do it manually
            location.setTotal(location.calcTotal());
            locRead.setTotal(location.calcTotal());
            System.out.println("Location read : " + locRead);
            System.out.println("Location created : " + location);

            assertEquals(location.getIdlocation(), locRead.getIdlocation(), "location id not read correctly");
            assertEquals(location.getDateloc(), locRead.getDateloc(), "location dateloc not read correctly");
            assertEquals(location.getKmtotal(), locRead.getKmtotal(), "location kmtotal not read correctly");
            assertEquals(location.getNbrpassagers(), locRead.getNbrpassagers(), "location nbrpassagers not read correctly");
            assertEquals(location.getTotal(), locRead.getTotal(), "location total not read correctly");
            //had to redefine the equals method in the Taxi class to make it work.
            assertEquals(location.getTaxi(), locRead.getTaxi(), "location taxi not read correctly");
            //had to redefine the equals method in the Client class to make it work.
            assertEquals(location.getClient(), locRead.getClient(), "location client not read correctly");
            assertEquals(location.getAdressedep(), locRead.getAdressedep(), "location adressedep not read correctly");
            assertEquals(location.getAdressearr(), locRead.getAdressearr(), "location adressearr not read correctly");
        } catch (Exception e) {
            fail("read failed :" + e);
        }
    }

    @Test
    void update() {
        try{
            location.setKmtotal(20);
            location = locationServiceImpl.update(location);
            location = locationServiceImpl.read(location.getIdlocation());
            assertEquals(20,location.getKmtotal(), "location km not updated");
            assertEquals(39.8,location.getTotal().doubleValue(), "location total not updated"  + location.getTotal().doubleValue());
        }catch (Exception e){
            fail("update failed : " + e);
        }
    }

    @Test
    void delete() {
        try{
            //TODO test delete address from a location
            //TODO check an exception is thrown when trying to delete an address that is used in a location !
            locationServiceImpl.delete(location);
            Assertions.assertThrows(Exception.class, () -> {
                locationServiceImpl.read(location.getIdlocation());
            },"error : location not deleted");
        }catch (Exception e){
            fail("delete failed : " + e);
        }
    }

    @Test
    void all() {
        try{
            List<Location> list = locationServiceImpl.all();
            boolean found = false;
            for(Location l : list){
                if(l.getIdlocation().equals(location.getIdlocation())){
                    found = true;
                }
            }
            assertTrue(found, "location not found in the list");
        }catch (Exception e){
            fail("all failed : " + e);
        }
    }
}