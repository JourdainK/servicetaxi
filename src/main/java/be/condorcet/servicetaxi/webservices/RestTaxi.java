package be.condorcet.servicetaxi.webservices;

import be.condorcet.servicetaxi.Services.InterfTaxiService;
import be.condorcet.servicetaxi.model.Location;
import be.condorcet.servicetaxi.model.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/taxis")
public class RestTaxi {

    @Autowired
    private InterfTaxiService taxiServiceImpl;

    //search taxi by id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Taxi> getTaxi(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("Seeking a taxi with id " + id);
        Taxi taxi = taxiServiceImpl.read(id);
        return new ResponseEntity<>(taxi, HttpStatus.OK);
    }

    //create taxi
   @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Taxi> createTaxi(@RequestBody Taxi taxi) throws Exception {
        System.out.println("Creating a taxi " + taxi);
        taxiServiceImpl.create(taxi);
        return new ResponseEntity<>(taxi, HttpStatus.OK);
    }

    //update taxi
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Taxi> updateTaxi(@PathVariable(value = "id") int id, @RequestBody Taxi taxi) throws Exception{
        System.out.println("Updating a taxi with id " + id);
        Taxi taxiUpdated = taxiServiceImpl.update(taxi);
        return new ResponseEntity<>(taxiUpdated, HttpStatus.OK);
    }

    //delete taxi
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Taxi> deleteTaxi(@PathVariable(value = "id")int id) throws Exception{
        System.out.println("Deleting a taxi with id " + id);
        Taxi taxi = taxiServiceImpl.read(id);
        taxiServiceImpl.delete(taxi);
        return new ResponseEntity<>(taxi, HttpStatus.OK);
    }

    //get all taxis
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Taxi>> getAllTaxis() throws Exception{
        System.out.println("Getting all taxis");
        List<Taxi> taxis = taxiServiceImpl.all();
        return new ResponseEntity<>(taxis, HttpStatus.OK);
    }

    //get all locations by taxi
    @RequestMapping(value = "/idtaxi={id}", method = RequestMethod.GET)
    public ResponseEntity<List<Location>> getLocationsByTaxi(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("Getting all locations by taxi with id " + id);
        Taxi taxi = taxiServiceImpl.read(id);
        List<Location> llocs = taxiServiceImpl.getLocationsByTaxi(taxi);
        return new ResponseEntity<>(llocs, HttpStatus.OK);
    }

    //exception handling
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleIOException(Exception e){
        System.out.println("Exception handled: " + e.getMessage());
        return ResponseEntity.notFound().header("Error", e.getMessage()).build();
    }



}
