package be.condorcet.servicetaxi.webservices;

import be.condorcet.servicetaxi.Services.InterfAdresseService;
import be.condorcet.servicetaxi.model.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class RestAdresse {
    @Autowired
    private InterfAdresseService adresseServiceImpl;

    //search address by id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Adresse> getAdresse(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("Seeking an adress with id " + id);
        Adresse adresse = adresseServiceImpl.read(id);
        return new ResponseEntity<>(adresse, HttpStatus.OK);
    }

    //create address
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Adresse> createAdresse(@RequestBody Adresse adresse) throws Exception {
        System.out.println("Creating an address " + adresse);
        adresseServiceImpl.create(adresse);
        return new ResponseEntity<>(adresse, HttpStatus.OK);
    }

    //update address
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Adresse> updateAddress(@PathVariable(value = "id") int id, @RequestBody Adresse updAdresse) throws Exception{
        System.out.println("Updating an address with id " + id);
        updAdresse.setIdadresse(id);
        Adresse adresseUpdated = adresseServiceImpl.update(updAdresse);
        return new ResponseEntity<>(adresseUpdated, HttpStatus.OK);
    }

    //delete address
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Adresse> deleteAddress(@PathVariable(value = "id")int id) throws Exception{
        System.out.println("Deleting an address with id " + id);
        Adresse adresse = adresseServiceImpl.read(id);
        adresseServiceImpl.delete(adresse);
        return new ResponseEntity<>(adresse, HttpStatus.OK);
    }

    //get all addresses
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Adresse>> getAllAddresses() throws Exception{
        System.out.println("Getting all addresses");
        List<Adresse> adresses = adresseServiceImpl.all();
        return new ResponseEntity<>(adresses, HttpStatus.OK);
    }

    //Exception handling
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleIOException(Exception e) {
        System.out.println("Exception handled : " + e.getMessage());
        return ResponseEntity.notFound().header("Error ", e.getMessage()).build();
    }


}
