package uasz.sn.gestion_enseignements.utilisateur.model;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.gestion_enseignements.utilisateur.model.Utilisateur;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public abstract class Enseignant extends Utilisateur {
    private String specialite;

}
