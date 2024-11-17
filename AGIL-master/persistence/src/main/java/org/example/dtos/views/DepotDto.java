package org.example.dtos.views;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.utils.Auditable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepotDto extends Auditable {

    private Long id;

    private String nom;

    private String adresse;

    private String telephone;

    private UserDto responsable;

}
