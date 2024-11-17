package org.example.controllers.pub;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.example.dtos.views.BonTravailDTO;
import org.example.requests.CreateBTRequest;
import org.example.services.BonTravailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bontravails")
@RequiredArgsConstructor
public class BonTravailController {

    private final BonTravailService bonTravailService;

    @GetMapping
    public ResponseEntity<List<BonTravailDTO>> getAll() {
        List<BonTravailDTO> allBonTravail = bonTravailService.getAll();
        return ResponseEntity.ok(allBonTravail);
    }

    @GetMapping("/{btId}")
    public ResponseEntity<BonTravailDTO> getById(@Parameter(name = "btId") @PathVariable("btId") Long btId) {
        BonTravailDTO bonTravailDTO = bonTravailService.getById(btId);
        return ResponseEntity.ok(bonTravailDTO);
    }

    @GetMapping("/technicien/{techId}")
    public ResponseEntity<List<BonTravailDTO>> getAllByTechnicienId(@Parameter(name = "techId") @PathVariable("techId") Long techId) {
        List<BonTravailDTO> bonTravailList = bonTravailService.getAllByTechnicienId(techId);
        return ResponseEntity.ok(bonTravailList);
    }

}
