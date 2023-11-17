package be.condorcet.servicetaxi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

// underscore was causing trouble in the variable from the SQL table, got help from Arthur Lorfevre And Daniele Nicolo
//https://stackoverflow.com/questions/23456197/spring-data-jpa-repository-underscore-on-entity-column-name
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APITCLIENT", schema = "ORA9", catalog = "OCRL.CONDORCET.BE")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @SequenceGenerator(name = "client_generator", sequenceName = "APITCLIENT_SEQ", allocationSize = 1)
    @Column(name = "id_client")
    private Integer idclient;

    //trouble with underscore and database
    //Thanks to Arthur Lorfèvre for the tips : https://stackoverflow.com/questions/23456197/spring-data-jpa-repository-underscore-on-entity-column-name
    @NonNull
    private String mail;
    @NonNull @Column(name = "nom_cli")
    private String nomcli;
    @NonNull @Column(name = "prenom_cli")
    private String prenomcli;
    @NonNull
    private String tel;
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    @ToString.Exclude
    private List<Location> llocations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(idclient, client.idclient) && Objects.equals(mail, client.mail) && Objects.equals(nomcli, client.nomcli) && Objects.equals(prenomcli, client.prenomcli);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idclient, mail, nomcli, prenomcli);
    }
}
