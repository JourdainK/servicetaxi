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
    @NonNull
    @ManyToOne @JoinColumn(name = "id_taxi")
    private Taxi taxi;
    @NonNull
    @ManyToOne @JoinColumn(name = "id_client")
    private Client client;
    @NonNull
    @ManyToOne @JoinColumn(name = "id_adresse")
    private Adresse adressedep;
    @NonNull
    @ManyToOne @JoinColumn(name = "id_adresse_1")
    private Adresse adressearr;

    //TODO check @FORMULA
    //had a lot of troubles with the trigger in the database that calculate the price
    //calculate the total in the model
    //I know it's not the best practice, but it works
    public BigDecimal calcTotal(){
        BigDecimal tot = this.taxi.getPrixkm().multiply(BigDecimal.valueOf(this.kmtotal));
        return tot;
    }

}
