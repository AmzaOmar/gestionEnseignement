package uasz.sn.gestion_enseignement.Maquette.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uasz.sn.gestion_enseignement.Authentification.model.Utilisateur;
import uasz.sn.gestion_enseignement.Authentification.service.UtilisateurService;
import uasz.sn.gestion_enseignement.Maquette.model.EC;
import uasz.sn.gestion_enseignement.Maquette.model.Maquette;
import uasz.sn.gestion_enseignement.Maquette.model.UE;
import uasz.sn.gestion_enseignement.Maquette.service.MaquetteService;
import uasz.sn.gestion_enseignement.Maquette.service.UEService;

import java.security.Principal;

import java.util.List;
import java.util.Objects;

@Controller
public class UEController {
    @Autowired
    private UEService ueService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private MaquetteService maquetteService;

    @GetMapping("/ChefDepartement/Maquette/{maquetteid}/UE")
    public String ue(@PathVariable("maquetteid") String id, Model model, Principal principal) {
        Utilisateur user = utilisateurService.findUtilisateur(principal.getName());
        Long Id = Long.parseLong(id);
        Maquette maquette=maquetteService.findById(Id);
        List<UE> ues = maquette.getUes();
        model.addAttribute("maquette", maquette);
        model.addAttribute("ues", ueService.findUEByMaquette(maquette));
        model.addAttribute("nom", user.getNom());
        model.addAttribute("prenom", user.getPrenom().charAt(0));
        return "ue";
    }

    @PostMapping("/ChefDepartement/Maquette/{maquetteid}/UE/modifierUE")
    public String modifierUE(@PathVariable("maquetteid") String id, UE ue) {
        Long Id = Long.parseLong(id);
        Maquette maquette=maquetteService.findById(Id);
        ueService.updateUE(ue);
        List<UE> ues = maquette.getUes();
        ues.removeIf(e -> Objects.equals(e.getId(), ue.getId()));
        ues.add(ue);
        maquette.setUes(ues);
        maquetteService.updateMaquette(maquette);
        return "redirect:/ChefDepartement/Maquette/" + id + "/UE";
    }

    @PostMapping("/ChefDepartement/Maquette/{maquetteid}/UE/supprimerUE")
    public String supprimerUE(@PathVariable("maquetteid") String id, UE ue) {
        Long Id = Long.parseLong(id);
        Maquette maquette=maquetteService.findById(Id);
        ueService.deleteUE(ue.getId());
        List<UE> ues = maquette.getUes();
        ues.removeIf(e -> Objects.equals(e.getId(), ue.getId()));
        maquette.setUes(ues);
        maquetteService.updateMaquette(maquette);
        return "redirect:/ChefDepartement/Maquette/" + id + "/UE";
    }

    @PostMapping("/ChefDepartement/Maquette/{maquetteid}/UE/ajouterUE")
    public String ajouterUE(@PathVariable("maquetteid") String id,UE ue) {
        Long Id = Long.parseLong(id);
        Maquette maquette=maquetteService.findById(Id);
        ue.setMaquette(maquette);

        ueService.addUE(ue);
        List<UE> ues =maquette.getUes();
        ues.add(ue);
        maquette.setUes(ues);
        maquetteService.updateMaquette(maquette);
        return "redirect:/ChefDepartement/Maquette/" + id +"/UE";
    }





}