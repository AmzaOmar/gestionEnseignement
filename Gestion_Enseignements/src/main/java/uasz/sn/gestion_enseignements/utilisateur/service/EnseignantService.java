package uasz.sn.gestion_enseignements.utilisateur.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.gestion_enseignements.utilisateur.model.Enseignant;
import uasz.sn.gestion_enseignements.utilisateur.repository.EnseignantRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EnseignantService {
    private EnseignantRepository enseignantRepository;


    public Enseignant ajouter(Enseignant enseignant){
        return enseignantRepository.save(enseignant);
    }

    public List<Enseignant> lister(){
        return enseignantRepository.findAll();
    }
    public Enseignant rechercher(Long id){
        return enseignantRepository.findById(id).get();
    }
    public Enseignant modifier(Enseignant enseignant){
        return enseignantRepository.save(enseignant);
    }
    public void supprimer(Long id){
        enseignantRepository.deleteById(id);
    }
}
