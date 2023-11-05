package be.condorcet.servicetaxi.Services;

import be.condorcet.servicetaxi.model.Adresse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdresseServiceImplTest {

    private Adresse address;

    @Autowired
    private AdresseServiceImpl adresseServiceImpl;
    @BeforeEach
    void setUp() {
        try{
            address = new Adresse(null,1000,"Bruxelles","Rue Test","10A");
            adresseServiceImpl.create(address);
            System.out.println("address is created : " + address);

        }catch (Exception e){
            System.out.println("Exception thrown while creating the address : " + e.getMessage() + '\n' + e.getStackTrace());
        }
    }

    @AfterEach
    void tearDown() {
        try{
            adresseServiceImpl.delete(address);
            System.out.println("address is deleted : " + address);
        }catch (Exception e){
            System.out.println("Exception thrown while deleting the address : " + e.getMessage() + '\n' + e.getStackTrace());
        }
    }

    @Test
    void create() {
        assertNotEquals(0, address.getIdadresse(),"address is not created, id is 0");
        assertNotEquals(null, address.getCp(),"address is not created, cp is null");
        assertEquals(1000, address.getCp(),"address has a problem, cp is not 1000");
        assertNotEquals(null, address.getLocalite(),"address is not created, localite is null");
        assertEquals("Bruxelles", address.getLocalite(),"address has a problem, localite is not Bruxelles");
        assertNotEquals(null, address.getRue(),"address is not created, rue is null");
        assertEquals("Rue Test", address.getRue(),"address has a problem, rue is not Rue Test");
        assertNotEquals(null, address.getNum(),"address is not created, num is null");
        assertEquals("10A", address.getNum(),"address has a problem, num is 10A");
    }

    @Test
    void read() {
        try{
            Adresse adr = adresseServiceImpl.read(address.getIdadresse());
            System.out.println("address is read : " + adr);
            System.out.println("address is read : " + address);
            assertEquals(address, adr, "address is not read correctly");
        }catch (Exception e){
            fail("Adresse.read() failed : " + e);
        }
    }

    @Test
    void update() {
        try{
            address.setCp(2000);
            address.setLocalite("Anvers");
            address.setRue("Rue Test2");
            address.setNum("20B");
            adresseServiceImpl.update(address);
            Adresse adr = adresseServiceImpl.read(address.getIdadresse());
            System.out.println("address is updated : " + adr);
            System.out.println("address is updated : " + address);
            assertEquals(address, adr, "address is not updated correctly");
            assertEquals(2000, address.getCp(),"address has a problem, cp is not 2000");
            assertEquals("Anvers", address.getLocalite(),"address has a problem, localite is not Anvers");
            assertEquals("Rue Test2", address.getRue(),"address has a problem, rue is not Rue Test2");
            assertEquals("20B", address.getNum(),"address has a problem, num is 20B");
            assertNotEquals(null, address.getIdadresse(),"address is not updated, id is null");
        }catch (Exception e){
            fail("Adresse.update() failed : " + e);
        }
    }

    @Test
    void delete() {
        try{
            adresseServiceImpl.delete(address);
            System.out.println("address is deleted : " + address);
            Assertions.assertThrows(Exception.class, () -> {
                adresseServiceImpl.read(address.getIdadresse());
            },"Error : address is not deleted");
        }catch (Exception e){
            fail("Adresse.delete() failed : " + e);
        }
    }

    @Test
    void all() {
        try{
            List<Adresse> list = adresseServiceImpl.all();
            boolean found = false;
            for(Adresse a : list){
                if(a.getIdadresse().equals(address.getIdadresse())){
                    found = true;
                }
            }
            assertTrue(found, "address not found in the list");
        }catch (Exception e){
            fail("Adresse.all() failed : " + e);
        }
    }
}