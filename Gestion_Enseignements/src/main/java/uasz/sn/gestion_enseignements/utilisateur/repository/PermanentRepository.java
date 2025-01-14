package uasz.sn.gestion_enseignements.utilisateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.gestion_enseignements.utilisateur.model.Permanent;

public interface PermanentRepository extends JpaRepository<Permanent, Long> {
}
