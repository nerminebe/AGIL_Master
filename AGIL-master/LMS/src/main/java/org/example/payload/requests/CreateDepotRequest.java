package org.example.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.daos.entities.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDepotRequest {

    private Long id;

    private String nom;

    private String adresse;

    private String telephone;

    private Long responsable_id;

}
