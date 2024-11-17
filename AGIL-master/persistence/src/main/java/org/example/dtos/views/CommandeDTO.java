package org.example.dtos.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDTO {
    private Long id;
    private UserDto gerant;
    private double montantTotal;
    private String status;
    private Date dateLivraison;
}
