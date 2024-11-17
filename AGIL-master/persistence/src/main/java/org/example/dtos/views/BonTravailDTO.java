package org.example.dtos.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.StatusBonTravail;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BonTravailDTO {
    private Long id;
    private UserDto technicien;
    private ReclamationDTO reclamation;
    private Date dateIntervention;
    private String detailsIntervention;
    private StatusBonTravail status;
    private Date enCoursLe;
    private Date termineLe;
    private Date clotureLe;
}
