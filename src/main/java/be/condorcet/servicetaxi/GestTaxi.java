package be.condorcet.servicetaxi;


import be.condorcet.servicetaxi.Repositories.ClientRepository;
import be.condorcet.servicetaxi.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/service")
public class GestTaxi {

    @Autowired
    ClientRepository clientRepository;

    @RequestMapping("/clients")
    public String affTous(Map<String, Object> model){
        System.out.println("Recherche clients");
        List<Client> list;
        try{
            list = clientRepository.findAll();

            model.put("myClients",list);
        }catch (Exception e){
            System.out.println("--------------Erreur lors de la recherche --------------------\n" + e);
            return "error";
        }
        return "printAllClients";
    }
}
