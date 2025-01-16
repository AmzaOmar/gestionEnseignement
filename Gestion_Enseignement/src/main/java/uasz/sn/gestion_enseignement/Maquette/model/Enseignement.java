package uasz.sn.gestion_enseignement.Maquette.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Enseignement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @OneToMany(mappedBy = "enseignement",orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Choix> choix;
    @OneToMany(mappedBy = "enseignement",orphanRemoval = true,fetch = FetchType.EAGER)
    private List<UE> ues;

    @OneToMany(mappedBy = "enseignement",orphanRemoval = true,fetch = FetchType.EAGER)
    private List<EC> ecs;


}
