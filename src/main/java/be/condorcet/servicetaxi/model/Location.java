package be.condorcet.servicetaxi.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

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

    @Formula("kmtotal * (select t.prixkm from APITAXI t where t.id_taxi = id_taxi)")
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

    public BigDecimal calcTotal(){
        BigDecimal tot = this.taxi.getPrixkm().multiply(BigDecimal.valueOf(this.kmtotal));
        return tot;
    }

}
