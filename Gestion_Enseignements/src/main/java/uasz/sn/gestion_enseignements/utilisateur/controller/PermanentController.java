package uasz.sn.gestion_enseignements.utilisateur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.gestion_enseignements.utilisateur.model.Utilisateur;
import uasz.sn.gestion_enseignements.Authentification.service.UtilisateurService;

import java.security.Principal;

@Controller
public class PermanentController {
    @Autowired
    private UtilisateurService utilisateurService;

    @RequestMapping(value = "/Permanent/Accueil",method = RequestMethod.GET )
    public String accueil_Permanent(Model model, Principal principal) {
        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        return "template_Permanent";
    }

    @RequestMapping(value = "/ChefDepartement/Accueil",method = RequestMethod.GET )
    public String accueil_ChefDepartement(Model model, Principal principal) {
        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        return "template_ChefDepartement";
    }
}
