package uasz.sn.gestion_enseignements.Authentification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uasz.sn.gestion_enseignements.Authentification.model.Role;
import uasz.sn.gestion_enseignements.utilisateur.model.Utilisateur;
import uasz.sn.gestion_enseignements.Authentification.repository.RoleRepository;
import uasz.sn.gestion_enseignements.Authentification.repository.UtilisateurRepository;


@Service
@Transactional
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Role ajouter_role(Role role) {
        return roleRepository.save(role);
    }

    public void ajouter_UtilisateurRoles(Utilisateur utilisateur, Role role) {
        Utilisateur u = utilisateurRepository.findByUsername(utilisateur.getUsername());
        Role profil = roleRepository.findByRole(role.getRole());
        u.getRoles().add(profil);
    }

    public Utilisateur rechercher_Utilisateur(String username) {
        return utilisateurRepository.findByUsername(username);
    }
    public Utilisateur findUtilisateur(String name){
        return utilisateurRepository.findByUsername(name);
    }

    public void updateUtilisateur(Utilisateur utilisateur) {
        Utilisateur user = utilisateurRepository.findByUsername(utilisateur.getUsername());
        utilisateur.setDateCreation(user.getDateCreation());
        utilisateur.setActive(user.isActive());
        utilisateur.setRoles(user.getRoles());
        utilisateurRepository.save(utilisateur);
    }

}