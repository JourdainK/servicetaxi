package be.condorcet.servicetaxi;


import be.condorcet.servicetaxi.Repositories.ClientRepository;
import be.condorcet.servicetaxi.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/service")
public class GestClient {

    //TODO remake the code with ClientServiceImpl
    @Autowired
    ClientRepository clientRepository;

    //page to see all clients
    @RequestMapping("/clients")
    public String affTous(Map<String, Object> model){
        System.out.println("Recherche clients");
        List<Client> list;
        try{
            list = clientRepository.findAll();
            list.sort((o1, o2) -> o1.getIdclient() - o2.getIdclient());
            model.put("myClients",list);
        }catch (Exception e){
            System.out.println("--------------Erreur lors de la recherche --------------------\n" + e);
            return "error";
        }
        return "Client/printAllClients";
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
        return "Client/newClient";
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
        return "Client/printClient";
    }

    //Couldn't use the Optional<Collection<client>> correctly -> got help from ChatGPT
    //collection because there might be more than one person with the same name
    @RequestMapping("/clientByName")
    String selectByName(@RequestParam("nomcli") String nomcli, Map<String, Object> model) {
        List<Client> clients = new ArrayList<>();

        try {
            Optional<List<Client>> lc = Optional.ofNullable(clientRepository.findByNomcli(nomcli));

            if (lc.isPresent()) {
                clients = lc.orElse(new ArrayList<>()); // Convert Optional to List with default value
                //System.out.println(clients);
                model.put("myClient", clients);
            }
            else{
                System.out.println("error while fetching client by name");
                return "error";
            }

        } catch (Exception e) {
            System.out.println("Error trying to get clients by name: " + e);
            model.put("error", e.getMessage());
            return "error";
        }

        return "Client/cliByName";
    }

    @RequestMapping("/deleteClient")
    String delete(@RequestParam("idclient") int idclient, Map<String, Object> model) {
        try {
            clientRepository.deleteById(idclient);
            List<Client> listClient = clientRepository.findAll();
            listClient.sort((o1, o2) -> o1.getIdclient() - o2.getIdclient());
            model.put("myClients", listClient);
        } catch (Exception e) {
            System.out.println("Error while deleting client: " + e);
            model.put("error", e.getMessage());
            return "error";
        }

        return "Client/deleteClient";
    }

}
