package be.condorcet.servicetaxi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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
    //fixed scale 2, rounded half up, so we don't loose $$ on the way
    //BigDecimal prixKm = new BigDecimal(1.99).setScale(2, RoundingMode.HALF_UP);
    private BigDecimal prixkm;
    @JsonIgnore
    @OneToMany(mappedBy = "taxi")
    @ToString.Exclude
    private List<Location> llocs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taxi taxi = (Taxi) o;
        return Objects.equals(idtaxi, taxi.idtaxi) && Objects.equals(immatriculation, taxi.immatriculation) && Objects.equals(nbremaxpassagers, taxi.nbremaxpassagers) && Objects.equals(prixkm, taxi.prixkm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtaxi, immatriculation, nbremaxpassagers, prixkm);
    }
}
