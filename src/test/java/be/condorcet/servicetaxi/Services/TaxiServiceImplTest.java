package be.condorcet.servicetaxi.Services;

import be.condorcet.servicetaxi.model.Location;
import be.condorcet.servicetaxi.model.Taxi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaxiServiceImplTest {
    private Taxi taxi;
    @Autowired
    private TaxiServiceImpl taxiServiceImpl;

    @Autowired
    private LocationServiceImpl LocationServiceImpl;


    @BeforeEach
    void setUp() {
        try{
            BigDecimal prixKm = new BigDecimal(2.99).setScale(2, RoundingMode.HALF_UP);
            taxi = new Taxi(null,"T-XXX-001",4,prixKm,new ArrayList<>());
            taxiServiceImpl.create(taxi);
            taxi = taxiServiceImpl.read(taxi.getIdtaxi());
            int numt = taxi.getIdtaxi();
            System.out.println("taxi is created : " + taxi + "\tTaxi id : "+numt);
        }catch (Exception e){
            fail("error while creating Taxi : "+e);

        }
    }

    @AfterEach
    void tearDown() {
        try{
            taxiServiceImpl.delete(taxi);
            System.out.println("taxi bas heen deleted : "+taxi);
        }catch (Exception e) {
            fail("error while deleting Taxi : " + e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,taxi.getIdtaxi(),"taxi created has not Id");
        assertNotEquals(null,taxi.getImmatriculation(),"Registration plate hasn't been encoded");
        assertEquals("T-XXX-001",taxi.getImmatriculation(),"Registration plate is not correct");
        assertNotEquals(null, taxi.getPrixkm(),"Price hasn't been set");
        assertEquals(new BigDecimal(2.99).setScale(2, RoundingMode.HALF_UP),taxi.getPrixkm(),"Price wasn't the one we encoded, error !");
        assertNotEquals(null, taxi.getNbremaxpassagers(),"Number of passengers is null, error !");
        assertEquals(4, taxi.getNbremaxpassagers(),"Incorrect number of passengers encoded, error !");
    }

    @Test void read() {
        try{
            int numTaxi = taxi.getIdtaxi();
            Taxi taxRead = taxiServiceImpl.read(numTaxi);
            assertEquals("T-XXX-001",taxRead.getImmatriculation(),"Taxi read is not the one we created");
            assertEquals(new BigDecimal(2.99).setScale(2, RoundingMode.HALF_UP),taxRead.getPrixkm(),"Price read is not the one we created");
            assertEquals(4, taxRead.getNbremaxpassagers(),"Number of passengers read is not the one we created");
            System.out.println("taxi read : " + taxRead);
        }catch (Exception e){
            fail("read failed : " + e);
        }
    }

    @Test
    void update() {
        try{
            taxi.setImmatriculation("T-XXX-002");
            taxi.setNbremaxpassagers(5);
            //old value 2.99
            taxi.setPrixkm(new BigDecimal(3.99).setScale(2, RoundingMode.HALF_UP));
            taxiServiceImpl.update(taxi);
            Taxi taxiUpdated = taxiServiceImpl.read(taxi.getIdtaxi());
            assertEquals("T-XXX-002",taxiUpdated.getImmatriculation(),"Taxi read hasn't been updated");
            assertEquals(new BigDecimal(3.99).setScale(2, RoundingMode.HALF_UP),taxiUpdated.getPrixkm(),"Price read hasn't been updated");
            assertEquals(5, taxiUpdated.getNbremaxpassagers(),"Number of passengers hasn't been updated");
            System.out.println("taxi updated : " + taxiUpdated);
        }catch (Exception e){
            fail("update failed : " + e);
        }
    }

    @Test
    void delete() {
        try{
            taxiServiceImpl.delete(taxi);
            Assertions.assertThrows(Exception.class, () -> {
                taxiServiceImpl.read(taxi.getIdtaxi());
            },"taxi hasn't been deleted");
        }catch (Exception e){
            fail("delete failed : " + e);
        }
    }

    @Test
    void all() {
        try{
            List<Taxi> list = taxiServiceImpl.all();
            boolean found = false;
            for(Taxi t : list){
                if(t.getIdtaxi().equals(taxi.getIdtaxi())){
                    found = true;
                }
            }
            assertTrue(found, "taxi not found in the list");
        }catch (Exception e){
            fail("failed, couldn't get all the Taxis : " + e);
        }
    }

    //I can't check locations for the taxi created for the tests
    //picking a taxi that has locations in the DB, and checking if the list is not empty
    @Test
    void getLocationsByTaxi(){
        try{
            Taxi taxiWithLocations = taxiServiceImpl.read(6);
            List<Location> list = taxiServiceImpl.getLocationsByTaxi(taxiWithLocations);
            //same method is present in LocationServiceImpl / LocationRepository
            boolean found = false;
            int i=1;
            for(Location l : list){
                System.out.println(i + " - " + l);
                if(l.getIdlocation() != null){
                    found = true;
                }
                i++;
            }
            assertTrue(found, "location not found in the list");
        }catch (Exception e){
            fail("failed, couldn't get all the Taxis : " + e);
        }
    }


    @Test
    void getLocationsBetween(){
        try{
            Taxi taxiWithLocations = taxiServiceImpl.read(1);
            List<Location> list = taxiServiceImpl.getLocationsBetween(taxiWithLocations, Date.valueOf("2023-01-01"),Date.valueOf("2023-12-31"));
            //same method is present in LocationServiceImpl / LocationRepository
            boolean found = false;
            int i=1;
            for(Location l : list){
                System.out.println(i + " - " + l);
                if(l.getIdlocation() != null){
                    found = true;
                }
                i++;
            }
            assertTrue(found, "location not found in the list");
        }catch (Exception e){
            fail("failed, couldn't get all the Taxis : " + e);
        }
    }
}