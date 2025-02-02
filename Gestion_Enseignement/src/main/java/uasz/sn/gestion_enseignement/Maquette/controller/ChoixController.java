package uasz.sn.gestion_enseignement.Maquette.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import uasz.sn.gestion_enseignement.Authentification.model.Utilisateur;
import uasz.sn.gestion_enseignement.Authentification.repository.UtilisateurRepository;
import uasz.sn.gestion_enseignement.Maquette.dto.ChoixDto;
import uasz.sn.gestion_enseignement.Maquette.model.Choix;
import uasz.sn.gestion_enseignement.Maquette.model.EC;
import uasz.sn.gestion_enseignement.Maquette.model.Enseignement;
import uasz.sn.gestion_enseignement.Maquette.repository.ChoixRepository;
import uasz.sn.gestion_enseignement.Maquette.repository.ECRepository;
import uasz.sn.gestion_enseignement.Maquette.repository.EnseignementRepository;
import uasz.sn.gestion_enseignement.Utilisateur.model.Permanent;
import uasz.sn.gestion_enseignement.Utilisateur.model.Vacataire;
import uasz.sn.gestion_enseignement.Utilisateur.repository.EnseignantRepository;
import uasz.sn.gestion_enseignement.Utilisateur.service.EnseignantService;
import uasz.sn.gestion_enseignement.Utilisateur.service.PermanentService;
import uasz.sn.gestion_enseignement.Utilisateur.service.VacataireService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
public class ChoixController {
    private final EnseignantRepository enseignantRepository;
    private final ChoixRepository choixRepository;
    private final EnseignementRepository enseignementRepository;
    private final EnseignantService enseignantService;
    private final UtilisateurRepository utilisateurRepository;
    private final VacataireService vacataireService;
    private final PermanentService permanentService;
    private final ECRepository ecRepository;



    @PostMapping("/soumettre-choix")
    public String ajouterChoix( ChoixDto choixDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        System.out.println("proff:"+email);
        Utilisateur utilisateur =utilisateurRepository.findByUsername(email);
        List<Vacataire> vacataires=vacataireService.getAllVacataires();
        List<Permanent> permanents=permanentService.getAllPermanents();
        System.out.println("choix"+permanents);
        Choix choix = new Choix();
        choix.setDate(LocalDate.now());
        if(utilisateur.getRoles().getFirst().getRole().equals("Permanent")){
            for(Permanent permanent:permanents){
                if(utilisateur.getUsername().equals(permanent.getUsername())){
                    choix.setEnseignant(permanent);
                    break;
                }
            }


        }else if(utilisateur.getRoles().getFirst().getRole().equals("Vacataire")){


            for(Vacataire vacataire:vacataires){
                if(utilisateur.getUsername().equals(vacataire.getUsername())){
                    choix.setEnseignant(vacataire);
                    break;
                }
            }
        }else if(utilisateur.getRoles().getFirst().getRole().equals("ChefDepartement")){
            for(Permanent permanent:permanents){
                if(utilisateur.getUsername().equals(permanent.getUsername())){
                    choix.setEnseignant(permanent);
                    break;
                }
            }
        }



        if(choixDto.getType().equals("cm")){
            choix.setType("cm");

        }else if(choixDto.getType().equals("td")){
            choix.setType("td");

        } else if (choixDto.getType().equals("tp")) {
            choix.setType("tp");

        }


        EC ec = ecRepository.findById(choixDto.getIdEc()).orElse(null);
       choix.setEc(ec);



        choixRepository.save(choix);







        return "genererMaquette";
    }
}
