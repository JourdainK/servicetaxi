package be.condorcet.servicetaxi;


import be.condorcet.servicetaxi.Services.InterfLocationService;
import be.condorcet.servicetaxi.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/locationServices")
    public String locationServices(Map<String, Object> model){
        return "locationServices";
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

        return "allLocations";
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
        return "printSeekLocation";
    }

    @RequestMapping("/createLocation")
    public String create(@RequestParam Date dateloc) {
        //TODO pick up here

        return null;
    }
}
