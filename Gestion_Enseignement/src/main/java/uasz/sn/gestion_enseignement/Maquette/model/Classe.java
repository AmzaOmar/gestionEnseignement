package uasz.sn.gestion_enseignement.Maquette.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String nom;


    @OneToOne(mappedBy = "classe",fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    private Maquette maquette;

    @ManyToOne
    private Formation formation;
}
