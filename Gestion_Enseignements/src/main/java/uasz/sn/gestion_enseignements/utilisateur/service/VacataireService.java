package uasz.sn.gestion_enseignements.utilisateur.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uasz.sn.gestion_enseignements.utilisateur.model.Etudiant;
import uasz.sn.gestion_enseignements.utilisateur.model.Vacataire;
import uasz.sn.gestion_enseignements.utilisateur.repository.VacataireRepository;

import java.util.List;

@Service
@Transactional
public class VacataireService {
    private VacataireRepository vacataireRepository;

    public Vacataire ajouter(Vacataire vacataire){
        return vacataireRepository.save(vacataire);
    }
    public List<Vacataire> lister(){
        return vacataireRepository.findAll();
    }
    public Vacataire rechercher(Long id){
        return vacataireRepository.findById(id).get();
    }
    public Vacataire modifier(Vacataire vacataire){
        return vacataireRepository.save(vacataire);
    }
    public void supprimer(Long id){
        vacataireRepository.deleteById(id);
    }

}
