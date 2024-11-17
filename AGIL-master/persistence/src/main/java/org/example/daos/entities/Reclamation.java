package org.example.daos.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.CategorieReclamation;
import org.example.enums.StatusReclamation;
import org.example.enums.TypeReclamation;
import org.example.utils.Auditable;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AGIL_Reclamation")
public class Reclamation extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User gerant;

    private TypeReclamation type;

    private CategorieReclamation categorieReclamation;

    private StatusReclamation status;

    private Date traiteeLe;
    private Date nonValideeLe;
    private Date clotureeLe;

    private String description;

    private Date dateReclamation;

    private Date dateResolution;

    private String observation;
}
