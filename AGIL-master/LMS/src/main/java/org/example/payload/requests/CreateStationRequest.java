package org.example.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.TypeStation;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateStationRequest {

    private String nom;

    private String adresse;


    private String matricule;

    private String telephone;

    private TypeStation typeStation;

    private Long gerant_id;

}
