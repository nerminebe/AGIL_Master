package org.example.payload.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StationProduitQteRequest {

    private Long stationId;
    private Long productId;
    private Long qte;

}
