package be.condorcet.servicetaxi.Services;

import be.condorcet.servicetaxi.model.Taxi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaxiServiceImplTest {
    private Taxi taxi;
    @Autowired
    private TaxiServiceImpl taxiServiceImpl;


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

    //TODO pick up here
    @Test    void read() {

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

    @Test
    void getTaxis() {
    }
}