package be.condorcet.servicetaxi.Services;

import be.condorcet.servicetaxi.model.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceImplTest {

    @Autowired
    private InterfClientService clientServiceImpl;

    @Autowired
    private InterfLocationService interfLocationService;

    Client client;
    @BeforeEach
    void setUp() {
        /*
        try{
            client =  new Client(null, "NameTest","SurnameTest","");
        }catch (Exception e){

        }
        //TODO when done with Location and Taxi which are the priority.
         */
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void read() {
    }

    @Test
    void testRead() {
    }

    @Test
    void create() {
    }

    @Test
    void testRead1() {
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