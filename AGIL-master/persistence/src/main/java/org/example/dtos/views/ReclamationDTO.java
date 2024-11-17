package org.example.dtos.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.CategorieReclamation;
import org.example.enums.StatusReclamation;
import org.example.enums.TypeReclamation;
import org.example.utils.Auditable;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReclamationDTO extends Auditable {

    private Long id;

    private UserDto gerant;

    private TypeReclamation type;

    private CategorieReclamation categorieReclamation;

    private StatusReclamation status;

    private String description;

    private Date dateReclamation;

    private Date dateResolution;

    private String observation;

}
