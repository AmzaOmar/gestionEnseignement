package uasz.sn.gestion_enseignement.Maquette.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uasz.sn.gestion_enseignement.Authentification.model.Utilisateur;
import uasz.sn.gestion_enseignement.Authentification.service.UtilisateurService;
import uasz.sn.gestion_enseignement.Maquette.model.Classe;
import uasz.sn.gestion_enseignement.Maquette.model.EC;
import uasz.sn.gestion_enseignement.Maquette.model.Maquette;
import uasz.sn.gestion_enseignement.Maquette.model.UE;
import uasz.sn.gestion_enseignement.Maquette.repository.ClasseRepository;
import uasz.sn.gestion_enseignement.Maquette.repository.MaquetteRepository;
import uasz.sn.gestion_enseignement.Maquette.service.ClasseService;
import uasz.sn.gestion_enseignement.Maquette.service.MaquetteService;
import uasz.sn.gestion_enseignement.Maquette.service.MaquetteService;
import uasz.sn.gestion_enseignement.Maquette.service.UEService;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Controller
public class MaquetteController {

    @Autowired
    private ClasseService classeService;

    @Autowired
    private MaquetteService maquetteService;

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private MaquetteRepository maquetteRepository;
    @Autowired
    private ClasseRepository classeRepository;

    // Afficher une classe avec sa maquette associée
    @GetMapping("/ChefDepartement/Classe/{classeId}/Maquette")
    public String maquette(@PathVariable("classeId") Long classeId, Model model, Principal principal) {
        Classe classe = classeService.findById(classeId);
        Maquette maquette = classe.getMaquette();
        Utilisateur user = utilisateurService.findUtilisateur(principal.getName());

        model.addAttribute("classe", classe);
        model.addAttribute("maquettes", maquetteRepository.findByClasse(classe));
        model.addAttribute("nom", user.getNom());
        model.addAttribute("prenom", user.getPrenom().charAt(0));
        return "maquette";
    }
    @PostMapping("/ChefDepartement/Classe/{classeid}/Maquette/modifierMaquette")
    public String modifierMaquette(@PathVariable("classeid") String id, Maquette maquette) {
        Long Id = Long.parseLong(id);
        Classe classe=classeService.findById(Id);
        maquetteService.updateMaquette(maquette);
        Maquette maquette1=classe.getMaquette();


        classe.setMaquette(maquette1);
        classeService.updateClasse(classe);
        return "redirect:/ChefDepartement/Classe/" + id + "/Maquette";
    }
    @PostMapping("/ChefDepartement/Classe/{classeid}/Maquette/supprimerMaquette")
    public String supprimerMaquette(@PathVariable("classeid") String id, Maquette maquette) {
        Long Id = Long.parseLong(id);
        Classe classe=classeService.findById(Id);
       System.out.println("classe: "+classe.getCode());
       maquette.setClasse(null);
       maquetteService.addMaquette(maquette);
       classe.setMaquette(null);

       classeService.addClasse(classe);



        maquetteService.deleteMaquette(maquette.getId());






        return "redirect:/ChefDepartement/Classe/" + id + "/Maquette";
    }

    @PostMapping("/ChefDepartement/Classe/{classeid}/Maquette/ajouterMaquette")
    public String ajouterMaquette(@PathVariable("classeid") String id,Maquette maquette) {
        Long Id = Long.parseLong(id);
        Classe classe=classeService.findById(Id);
        maquette.setClasse(classe);




        classe.setMaquette(maquette);
        classeService.addClasse(classe);
        return "redirect:/ChefDepartement/Classe/" + id +"/Maquette";
    }




}

