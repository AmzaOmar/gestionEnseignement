package uasz.sn.gestion_enseignements.utilisateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uasz.sn.gestion_enseignements.utilisateur.model.Administratif;

public interface AdministratifRepository extends JpaRepository<Administratif, String> {
}
