package be.condorcet.servicetaxi.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APILOCATION", schema="ORA9", catalog = "orcl.condorcet.be")
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_generator")
    @SequenceGenerator(name="location_generator", sequenceName = "APILOCATION_SEQ", allocationSize = 1)
    @Column(name = "id_location")
    private Integer idlocation;
    @NonNull
    private Date dateloc;
    @NonNull
    private Integer kmtotal;
    @NonNull
    private Integer nbrpassagers;
    //total will be calculated by a trigger in the database (INSERT_PRIXTOT_TRIGGER)
    private BigDecimal total;
    @ManyToOne @JoinColumn(name = "id_taxi")
    private Taxi taxi;
    @ManyToOne @JoinColumn(name = "id_client")
    private Client client;
    @OneToOne @JoinColumn(name = "id_adresse")
    private Adresse adressedep;
    @OneToOne @JoinColumn(name = "id_adresse_1")
    private Adresse adressearr;
}
