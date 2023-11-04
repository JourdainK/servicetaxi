package be.condorcet.servicetaxi;

import be.condorcet.servicetaxi.Services.InterfTaxiService;
import be.condorcet.servicetaxi.model.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/taxi")
public class GestTaxi {
    @Autowired
    private InterfTaxiService taxiServiceImpl;

    @RequestMapping("/taxiServices")
    public String taxiServices(Map<String, Object> model){
        return "Taxis/taxiServices";
    }


    //fixed the path
    @RequestMapping("/seekbyid")
    public String read(@RequestParam int idtaxi, Map<String, Object> model){
        //System.out.println("Seeking taxi by id  : " + idtaxi);
        try{
            Taxi taxi = taxiServiceImpl.read(idtaxi);
            System.out.println(taxi);
            model.put("myTaxi",taxi);
        }catch (Exception e){
            System.out.println("--------- Error while seeking the client -----------\n"  + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "Taxis/printSeekTaxi";
    }

    @RequestMapping("/createTaxi")
    public String create(@RequestParam String immatriculation, @RequestParam int nbremaxpassagers,
                         @RequestParam BigDecimal prixkm, Map<String, Object> model){
        Taxi taxi = new Taxi(immatriculation, nbremaxpassagers, prixkm);
        //System.out.println("Creating a taxi : " + taxi) ;
        try{
            taxiServiceImpl.create(taxi);
            model.put("newtaxi",taxi);
        }catch (Exception e){
            System.out.println("Error while creating the new taxi : " + e );
            model.put("error", e.getMessage());
            return "error";
        }

        return "Taxis/newTaxi";
    }

}
