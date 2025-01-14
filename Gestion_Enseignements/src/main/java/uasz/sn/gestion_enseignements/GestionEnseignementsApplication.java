package uasz.sn.gestion_enseignements;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import uasz.sn.gestion_enseignements.Authentification.model.Role;
import uasz.sn.gestion_enseignements.Authentification.service.UtilisateurService;
import uasz.sn.gestion_enseignements.utilisateur.model.Permanent;
import uasz.sn.gestion_enseignements.utilisateur.model.Vacataire;
import uasz.sn.gestion_enseignements.utilisateur.service.EnseignantService;

import java.util.Date;

@SpringBootApplication
public class GestionEnseignementsApplication implements CommandLineRunner {

    private final UtilisateurService utilisateurService;
    private final PasswordEncoder passwordEncoder;
    private final EnseignantService enseignantService;

    public GestionEnseignementsApplication(UtilisateurService utilisateurService, PasswordEncoder passwordEncoder, EnseignantService enseignantService)  {
        this.utilisateurService = utilisateurService;
        this.passwordEncoder = passwordEncoder;
        this.enseignantService = enseignantService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GestionEnseignementsApplication.class, args);
    }
    public void run(String... args) throws Exception{
        Role permanent = utilisateurService.ajouter_role(new Role("Permanent"));
        Role vacataire = utilisateurService.ajouter_role(new Role("Vacataire"));
        String password = passwordEncoder.encode("Passer123");

        Permanent user_1 = new Permanent();
        user_1.setNom("DIOP");user_1.setPrenom("Ibrahima");user_1.setUsername("idiop@uasz.sn");
        user_1.setPassword(password);user_1.setDateCreation(new Date()); user_1.setActive(true);
        user_1.setSpecialite("Web Semantique");
        user_1.setMatricule("ID2024"); user_1.setGrade("Professeur");
        enseignantService.ajouter(user_1);
        utilisateurService.ajouter_UtilisateurRoles(user_1, permanent);

        Vacataire user_2 = new Vacataire();
        user_2.setNom("MALACK");user_2.setPrenom("Camir");user_2.setUsername("cmalack@uasz.sn");
        user_2.setPassword(password);user_2.setDateCreation(new Date()); user_2.setActive(true);
        user_2.setSpecialite("Ingenierie de Connaissance");
//         user_1.setNiveau("Doctorant");
        enseignantService.ajouter(user_2);
        utilisateurService.ajouter_UtilisateurRoles(user_2, vacataire);
    }
}
