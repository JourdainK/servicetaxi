package be.condorcet.servicetaxi;


import be.condorcet.servicetaxi.Repositories.AdresseRepository;
import be.condorcet.servicetaxi.model.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/address")
public class GestAddress {

    @Autowired
    AdresseRepository adresseRepository;

    @RequestMapping("/allAddresses")
    String printAllAddresses(Map<String, Object> model){
        System.out.println("seeking addresses to print");
        List<Adresse> la;
        try{
            la = adresseRepository.findAll();
            model.put("myAddresses",la);
        }catch (Exception e){
            System.out.println("Error while looking for addresses : " + e.getMessage());
            return "error";
        }

        return "printAllAddresses";
    }

    @RequestMapping("createAddress")
    String createAddress(@RequestParam Integer cp, @RequestParam String localite, @RequestParam String rue, @RequestParam String num,
                         Map<String, Object> model) {
        System.out.println("Creating address");
        Adresse address = new Adresse(cp, localite,rue, num);
        try{
            adresseRepository.save(address);
            Collection<Adresse> colAddres = adresseRepository.findAll();
            model.put("colAddres", colAddres);
        }catch (Exception e){
            System.out.println("Error while creating a new address");
            model.put("Error" , e.getMessage());
        }

        return "TODO";
    }

    //TODO keep up with the rest of the code
}
