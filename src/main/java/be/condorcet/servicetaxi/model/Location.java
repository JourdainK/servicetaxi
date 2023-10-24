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
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_genrator")
    @SequenceGenerator(name="location_generator", sequenceName = "APILOCATION_SEQ", allocationSize = 1)
    @Column(name = "id_location")
    private Integer idlocation;
    @NonNull
    private Date dateloc;
    @NonNull
    private Integer kmtotal;
    private BigDecimal total;
    @ManyToOne @JoinColumn(name = "id_taxi")
    private Client client;

}
