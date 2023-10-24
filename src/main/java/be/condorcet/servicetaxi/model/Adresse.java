package be.condorcet.servicetaxi.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIADRESSE", schema="ORA9", catalog = "OCRL.CONDORCET.BE")
public class Adresse {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "adresse_generator")
    @SequenceGenerator(name = "adresse_generator", sequenceName = "APIADRESSE_SEQ" , allocationSize = 1)
    @Column(name = "id_adresse")
    private Integer idadresse;

    @NonNull
    private Integer cp;
    @NonNull
    private String localite;
    @NonNull
    private String rue;
    @NonNull
    private String num;
}
