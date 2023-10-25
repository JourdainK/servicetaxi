package be.condorcet.servicetaxi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APITAXI", schema = "ORA9", catalog = "OCRL.CONDORCET.BE")
public class Taxi {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taxi_generator")
    @SequenceGenerator(name = "taxi_generator", sequenceName = "APITAXI_SEQ", allocationSize = 1)
    @Column(name = "id_taxi")
    private Integer idtaxi;
    @NonNull
    private String immatriculation;
    @NonNull
    private Integer nbremaxpassagers;
    @NonNull
    private BigDecimal prixkm;

    @JsonIgnore
    @OneToMany(mappedBy = "taxi")
    @ToString.Exclude
    private List<Location> llocs;

}
