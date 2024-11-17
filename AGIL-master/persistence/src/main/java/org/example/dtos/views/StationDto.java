package org.example.dtos.views;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.TypeStation;
import org.example.utils.Auditable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StationDto extends Auditable {


    private Long id;

    private String nom;

    private String adresse;

    private String matricule;

    private String telephone;

    private TypeStation typeStation;

    private UserDto gerant;

}
