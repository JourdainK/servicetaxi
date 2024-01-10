package be.condorcet.servicetaxi.Services;

import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceImplTest {

    @Autowired
    private InterfClientService clientServiceImpl;

    Client client;
    @BeforeEach
    void setUp() {

        try{
            client =  new Client("test@gmail.com" ,"NameTest","SurnameTest","118218");
            clientServiceImpl.create(client);
            System.out.println("client is created : " + client);
        }catch (Exception e){
            fail("error while creating Client : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try{
            clientServiceImpl.delete(client);
            System.out.println("client is deleted : " + client);
        }catch (Exception e){
            fail("error while deleting Client : "+e);
        }
    }

    @Test
    void read() {
        try {
            int id = client.getIdclient();
            Client client1 = null;
            client1 = clientServiceImpl.read(id);
            assertEquals(client,client1,"client read is not the same as the client created");
            assertEquals("NameTest", client.getNomcli(),"client name is not the same as the client created");
            assertEquals("SurnameTest", client.getPrenomcli(),"client lastname is not the same as the client created");
            assertEquals("118218", client.getTel(),"client telephone is not the same as the client created");
            assertEquals("test@gmail.com", client.getMail(),"client mail is not the same as the client created");
            assertNotEquals(null, client.getMail(),"client mail is null");
            assertNotEquals(null,client.getPrenomcli(),"client lastname is null");
            assertNotEquals(null ,client.getTel(),"client telephone is null");
        } catch (Exception e) {
            fail("read failed " + e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,client.getIdclient(),"client created has not Id");
        assertNotEquals(null,client.getIdclient(),"client created is null");
        assertNotEquals(null,client.getMail(),"client mail is null");
        assertNotEquals(null,client.getNomcli(),"client name is null");
        assertNotEquals(null,client.getPrenomcli(),"client lastname is null");
        assertNotEquals(null,client.getTel(),"client telephone is null");
    }

    @Test
    void update() {
        try{
            client.setMail("testUpdate@gmail.com");
            client.setNomcli("NameTestUpdate");
            client.setPrenomcli("SurnameTestUpdate");
            client.setTel("218118");
            clientServiceImpl.update(client);
            client = clientServiceImpl.read(client.getIdclient());
            assertEquals("testUpdate@gmail.com", client.getMail(),"client mail is not the same as the client created");
            assertEquals("NameTestUpdate", client.getNomcli(),"client name is not the same as the client created");
            assertEquals("SurnameTestUpdate", client.getPrenomcli(),"client lastname is not the same as the client created");
            assertEquals("218118", client.getTel(),"client telephone is not the same as the client created");
            assertNotEquals(null,client.getIdclient(),"client created is null");
            assertNotEquals(null,client.getMail(),"client mail is null");
            assertNotEquals(null,client.getNomcli(),"client name is null");
            assertNotEquals(null,client.getPrenomcli(),"client lastname is null");
            assertNotEquals(null,client.getTel(),"client telephone is null");
        }catch (Exception e){
            fail("update failed : " + e);
        }
    }

    @Test
    void delete() {
      try{
          clientServiceImpl.delete(client);
          System.out.println("client is deleted : " + client);
          Assertions.assertThrows(Exception.class, () -> {
              clientServiceImpl.read(client.getIdclient());
          },"Error : client is not deleted");
        }catch (Exception e){
            fail("delete failed : " + e);
      }
    }

    @Test
    void all() {
        try{
            List<Client> list = clientServiceImpl.all();
            boolean found = false;
            for(Client c : list){
                if(c.getIdclient().equals(client.getIdclient())){
                    found = true;
                }
            }
            assertTrue(found, "client not found in the list");
        }catch (Exception e){
            fail("all failed : " + e);
        }
    }

   //test get location of a client

}