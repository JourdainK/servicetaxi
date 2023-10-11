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
    private Integer id_client;

    @NonNull
    private String mail;

    private String nom_cli;
    private String prenom_cli;
    private String tel;
}
