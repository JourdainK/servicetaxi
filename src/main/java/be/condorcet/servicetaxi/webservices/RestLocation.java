package be.condorcet.servicetaxi.webservices;

import be.condorcet.servicetaxi.Services.InterfClientService;
import be.condorcet.servicetaxi.Services.InterfLocationService;
import be.condorcet.servicetaxi.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/locations")
public class RestLocation {
    @Autowired
    private InterfLocationService locationServiceImpl;
    //TODO check postman for other method than GET
    @Autowired
    private InterfClientService clientServiceImpl;

    //get location by id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Location> getLocation(@PathVariable(value = "id") int id)throws Exception{
        System.out.println("Seeking a location with id " + id);
        Location location = locationServiceImpl.read(id);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    //get a location by client ID
    @RequestMapping(value = "/client={id}", method = RequestMethod.GET)
    public ResponseEntity<List<Location>> getLocationByClient(@PathVariable(value = "id") int id)throws Exception{
        System.out.println("Seeking a location with client id " + id);
        List<Location> locations = locationServiceImpl.getLocationsByClient(clientServiceImpl.read(id));
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    //create a location
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Location> createLocation(@RequestBody Location location) throws Exception {
        System.out.println("Creating a location " + location + " for client " + location.getClient());
        // Extract only the date part from "dateloc" and create a java.sql.Date object
        java.sql.Date dateLoc = java.sql.Date.valueOf(location.getDateloc().toLocalDate());
        // Set the dateLoc in the location object - > keep consistency with what's already in DB
        location.setDateloc(dateLoc);
        locationServiceImpl.create(location);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    //update a location
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Location> updateLocation(@PathVariable(value = "id") int id,@RequestBody Location location) throws Exception{
        System.out.println("Updating a location with id " + id);
        java.sql.Date dateLoc = java.sql.Date.valueOf(location.getDateloc().toLocalDate());
        // Set the dateLoc in the location object - > keep consistency with what's already in DB
        location.setDateloc(dateLoc);
        location.setIdlocation(id);
        Location locationUpdated = locationServiceImpl.update(location);
        return new ResponseEntity<>(locationUpdated, HttpStatus.OK);
    }

    //delete a location
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Location> deleteLocation(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("Deleting a location with id " + id);
        Location location = locationServiceImpl.read(id);
        locationServiceImpl.delete(location);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    //get all locations
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Location>> getAllLocations() throws Exception{
        System.out.println("Getting all locations");
        List<Location> locations = locationServiceImpl.all();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    //handling errors
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> exceptionHandler(Exception e){
        System.out.println("Exception caught : " + e.getMessage());
        return ResponseEntity.notFound().header("error",e.getMessage()).build();
    }

}
