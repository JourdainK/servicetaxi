package be.condorcet.servicetaxi.webservices;

import be.condorcet.servicetaxi.Services.InterfClientService;
import be.condorcet.servicetaxi.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class RestClient {
    @Autowired
    private InterfClientService clientServiceImpl;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> getClient(@PathVariable(value = "id")int id) throws Exception{
        System.out.println("Seeking a client with id " + id);
        Client client = clientServiceImpl.read(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    //search client by last name, first name and mail
    @RequestMapping(value = "/{nom}/{prenom}/{tel}", method = RequestMethod.GET)
    public ResponseEntity<Client> getClientUnique(@PathVariable(value = "nom") String nom,
                                                  @PathVariable(value = "prenom") String prenom,
                                                  @PathVariable(value = "mail") String mail)  throws Exception{
        System.out.println("recherche du client "+nom+" "+prenom+" "+mail);
        Client client = clientServiceImpl.read(nom,prenom,mail);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    //search by name
    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getClientByNom(@PathVariable(value = "nom") String nom) throws Exception{
        System.out.println("Seeking a client with name " + nom);
        List<Client> clients = clientServiceImpl.read(nom);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    //create client
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Client> createClient(@RequestBody Client client) throws Exception {
        System.out.println("Creating a client " + client);
        clientServiceImpl.create(client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    //update client
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id") int id, @RequestBody Client client) throws Exception{
        System.out.println("Updating a client with id " + id);
        client.setIdclient(id);
        Client clientUpdated = clientServiceImpl.update(client);
        return new ResponseEntity<>(clientUpdated, HttpStatus.OK);
    }

    //delete client
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Client> deleteClient(@PathVariable(value = "id")int id) throws Exception{
        System.out.println("Deleting a client with id " + id);
        Client client = clientServiceImpl.read(id);
        clientServiceImpl.delete(client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    //get all clients
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getAllClients() throws Exception{
        System.out.println("Getting all clients");
        List<Client> clients = clientServiceImpl.all();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    //exception Handling
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> exceptionHandler(Exception e){
        System.out.println("Exception handled " + e.getMessage());
        return ResponseEntity.notFound().header("Error ", e.getMessage()).build();
    }





}
