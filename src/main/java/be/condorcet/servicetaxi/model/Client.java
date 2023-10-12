package be.condorcet.servicetaxi.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "APITCLIENT", schema = "ORA9", catalog = "OCRL.CONDORCET.BE")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @SequenceGenerator(name = "client_generator", sequenceName = "APITCLIENT_SEQ", allocationSize = 1)
    private Integer id_client;

    @NonNull
    private String mail;
    @NonNull @Column(name = "nom_cli")
    private String nomcli;
    @NonNull @Column(name = "prenom_cli")
    private String prenomcli;
    @NonNull
    private String tel;

}
