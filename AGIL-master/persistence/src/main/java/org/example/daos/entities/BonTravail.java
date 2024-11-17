package org.example.daos.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.StatusBonTravail;
import org.example.utils.Auditable;

import javax.persistence.*;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AGIL_BonTravail")
public class BonTravail extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User technicien;

    @ManyToOne
    private Reclamation reclamation;

    private Date dateIntervention;

    private String detailsIntervention;

    private StatusBonTravail status;

    private Date enCoursLe;
    private Date termineLe;
    private Date clotureLe;
}
