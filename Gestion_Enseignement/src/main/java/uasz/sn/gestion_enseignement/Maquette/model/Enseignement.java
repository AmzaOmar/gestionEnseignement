package uasz.sn.gestion_enseignement.Maquette.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.gestion_enseignement.Utilisateur.model.Enseignant;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enseignement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // CM, TD, TP


    @ManyToOne
    @JoinColumn(name = "ec_id", nullable = false)
    private EC ec; // L'EC validé par le chef de département

    @ManyToOne
    @JoinColumn(name = "enseignant_id", nullable = false)
    private Enseignant enseignant; // L'enseignant sélectionné

    private boolean valide; // Indique si le choix a été validé
}

