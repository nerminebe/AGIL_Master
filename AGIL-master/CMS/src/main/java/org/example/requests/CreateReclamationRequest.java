package org.example.requests;

import lombok.Data;
import org.example.enums.CategorieReclamation;
import org.example.enums.TypeReclamation;


@Data
public class CreateReclamationRequest {

    private Long gerantId;

    private TypeReclamation type;

    private CategorieReclamation categorieReclamation;

    private String description;

    private String observation;
}
