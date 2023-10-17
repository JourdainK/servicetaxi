package be.condorcet.servicetaxi.model;

import jakarta.persistence.*;
import lombok.*;

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

}
