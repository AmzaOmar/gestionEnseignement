package uasz.sn.gestion_enseignements.Authentification.controller;

import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.gestion_enseignements.utilisateur.model.Utilisateur;
import uasz.sn.gestion_enseignements.Authentification.service.UtilisateurService;


import java.security.Principal;
import java.util.Date;

@Controller
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @RequestMapping(value = "/login")
    public String index() {
        return "login";
    }

    @RequestMapping("/")
    public String login(Principal principal) {
        Utilisateur utilisateur = utilisateurService.findUtilisateur(principal.getName());
        System.out.println(utilisateur.getRoles());
        return "redirect:/etudiants";
    }

    @RequestMapping(value = "/logout")
    public String logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout=true";
    }

    @RequestMapping(value = "/modifierUtilisateur", method = RequestMethod.POST)
    public String modifierUtilisateur(Utilisateur utilisateur) {
        utilisateurService.updateUtilisateur(utilisateur);
        return "redirect:/profil";
    }


}