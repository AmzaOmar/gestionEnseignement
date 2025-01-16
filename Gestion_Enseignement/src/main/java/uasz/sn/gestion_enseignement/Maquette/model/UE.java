package uasz.sn.gestion_enseignement.Maquette.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle, code, description;
    private int credit, coefficient;
    @OneToMany(mappedBy = "ue", fetch = FetchType.EAGER)
    private List<EC> ecs;

    @ManyToOne

    private Maquette maquette;

    @ManyToOne
    @JoinColumn(name = "enseignement_id") // Spécifiez la clé étrangère
    private Enseignement enseignement;
}