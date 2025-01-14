package uasz.sn.gestion_enseignements.utilisateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.gestion_enseignements.utilisateur.model.Enseignant;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
}
