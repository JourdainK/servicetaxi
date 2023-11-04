package be.condorcet.servicetaxi;


import be.condorcet.servicetaxi.Services.InterfAdresseService;
import be.condorcet.servicetaxi.Services.InterfClientService;
import be.condorcet.servicetaxi.Services.InterfLocationService;
import be.condorcet.servicetaxi.Services.InterfTaxiService;
import be.condorcet.servicetaxi.model.Adresse;
import be.condorcet.servicetaxi.model.Client;
import be.condorcet.servicetaxi.model.Location;
import be.condorcet.servicetaxi.model.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/location")
public class GestLocation {

    @Autowired
    private InterfLocationService locationServiceImpl;
    @Autowired
    private InterfClientService clientServiceImpl;
    @Autowired
    private InterfTaxiService taxiServiceImpl;
    @Autowired
    private InterfAdresseService adresseServiceImpl;

    @RequestMapping("/locationServices")
    public String locationServices(Map<String, Object> model){
        return "Location/locationServices";
    }

    @RequestMapping("/allLocations")
    public String allLocations(Map<String, Object> model){
        System.out.println("Seeking all locations");
        List<Location> llocs;
        try{
            llocs = locationServiceImpl.all();
            llocs.sort((o1, o2) -> o1.getIdlocation() - o2.getIdlocation());
            model.put("myLocations",llocs);
        }catch (Exception e){
            System.out.println("Error while seeking all locations : " + e.getMessage());
            model.put("error",e.getMessage());
            return "error";
        }

        return "Location/allLocations";
    }

    @RequestMapping("/seekLocbyid")
    public String read(@RequestParam int idlocation, Map<String, Object> model){
        System.out.println("Seeking location by id  : " + idlocation);
        try{
            Location location = locationServiceImpl.read(idlocation);
            System.out.println(location);
            model.put("myLocation",location);
        }catch (Exception e){
            System.out.println("--------- Error while seeking the location -----------\n"  + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "Location/printSeekLocation";
    }

    @RequestMapping("/createLocation")
    public String create(@RequestParam Date dateloc, @RequestParam Integer kmtotal, @RequestParam Integer nbrepassagers,
                         @RequestParam int idtaxi, @RequestParam int idclient,@RequestParam int id_adresse, @RequestParam int id_adresse_1 ,Map<String, Object> model){

        try {
            Taxi taxi = taxiServiceImpl.read(idtaxi);
            Client client = clientServiceImpl.read(idclient);
            Adresse adressedep = adresseServiceImpl.read(id_adresse);
            Adresse adressearr = adresseServiceImpl.read(id_adresse_1);
            Location location = new Location(dateloc, kmtotal, nbrepassagers, taxi, client, adressedep, adressearr);
            locationServiceImpl.create(location);
            //update the location to get the price in, automatically inserted by the database (Trigger)
            //spring doesn't like the trigger so i created a method in the model to calculate the price
            Location updatedLocation = locationServiceImpl.read(location.getIdlocation());
            System.out.println("updated loc = " + updatedLocation);
            model.put("newlocation", updatedLocation);
        } catch (Exception e) {
            System.out.println("Error while seeking element for the Location : " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        System.out.println("returning to the new location page");

        return "Location/newLocation";
    }
}
