package be.condorcet.servicetaxi;


import be.condorcet.servicetaxi.Repositories.ClientRepository;
import be.condorcet.servicetaxi.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

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
    public String create(@RequestParam String mail, @RequestParam String nomcli,@RequestParam String prenomcli,
            @RequestParam String tel, Map<String, Object> model) {
        System.out.println("creating client");
        Client cl = new Client(mail,nomcli,prenomcli,tel);
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
    String selection(@RequestParam("idclient") int idclient, Map<String, Object> model) {

        Client client = null;
        Optional<Client> optionalClient = null;

        try{
            optionalClient = clientRepository.findById(idclient);

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

    // underscore was causing trouble in the variable from the SQL table, got help from Arthur Lorfevre And Daniele Nicolo
    //https://stackoverflow.com/questions/23456197/spring-data-jpa-repository-underscore-on-entity-column-name
    //avoiding the pain of having to change all the database

    @RequestMapping("clientByName")
    String selectByName(@RequestParam("nomcli") String nomcli, Map<String, Object> model) {
        Optional<List<Client>> optionalClients;
        try {
            optionalClients = Optional.ofNullable((List<Client>) clientRepository.findClientsByNomcli(nomcli));
            System.out.println(optionalClients);
            model.put("myClient", optionalClients.orElseGet(Collections::emptyList));
        } catch (Exception e) {
            System.out.println("Error trying to get clients by name: " + e);
            model.put("error", e.getMessage());
            return "error";
        }

        return "printClientByName";
    }


}
