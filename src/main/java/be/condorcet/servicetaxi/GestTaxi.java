package be.condorcet.servicetaxi;


import be.condorcet.servicetaxi.Repositories.ClientRepository;
import be.condorcet.servicetaxi.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/service")
public class GestTaxi {

    @Autowired
    ClientRepository clientRepository;

    //page to see all clients
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

    //create a new client
    @RequestMapping("/create")
    public String create(@RequestParam String mail, @RequestParam String nom_cli,@RequestParam String prenom_cli,
            @RequestParam String tel, Map<String, Object> model) {
        System.out.println("creating client");
        Client cl = new Client(mail,nom_cli,prenom_cli,tel);
        try{
            clientRepository.save(cl);
            //System.out.println(cl);
            Collection<Client> lcl = clientRepository.findAll();
            model.put("newcli", cl);
            model.put("allClients",lcl);
        }catch (Exception e){
            System.out.println("--------- Error while creating the new client -----------\n"  + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "newClient";
    }

    //select a client (look by id)
    @RequestMapping("/select")
    String selection(@RequestParam("id_client") int id_client, Map<String, Object> model) {

        Client client = null;
        Optional<Client> optionalClient = null;

        try{
            optionalClient = clientRepository.findById(id_client);

            if(optionalClient.isPresent())
            {
                client = optionalClient.get();
            }else
            {
                throw new Exception("No client found");
            }

            model.put("myClient",client);
        }catch (Exception e){
            System.out.println("Error while looking for a client :" + e);
            model.put("error", e);
            return "error";
        }
        return "printClient";
    }


}
