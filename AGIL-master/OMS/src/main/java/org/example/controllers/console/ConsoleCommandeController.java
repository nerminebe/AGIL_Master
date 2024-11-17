package org.example.controllers.console;

import lombok.RequiredArgsConstructor;
import org.example.dtos.views.CommandeDTO;
import org.example.dtos.views.LigneCommandeDTO;
import org.example.payload.requests.CreateCommandeRequest;
import org.example.payload.requests.UpdateCommandeRequest;
import org.example.payload.requests.UpdateLigneCommandeRequest;
import org.example.services.CommandeService;
import org.example.services.LigneCommandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("console/api/v1/")
@RequiredArgsConstructor
public class ConsoleCommandeController {

    private final CommandeService commandeService;
    private final LigneCommandeService ligneCommandeService;

    @PostMapping("/commandes")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<CommandeDTO> createCommande(@RequestBody CreateCommandeRequest request) {
        CommandeDTO newCommande = commandeService.createCommande(request);
        return new ResponseEntity<>(newCommande, HttpStatus.CREATED);
    }

    @GetMapping("/commandes/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<CommandeDTO> getCommandeById(@PathVariable Long id) {
        CommandeDTO commande = commandeService.getCommandeById(id);
        return ResponseEntity.ok(commande);
    }

    @GetMapping("/commandes")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<List<CommandeDTO>> getAllCommandes() {
        List<CommandeDTO> commandes = commandeService.getAllCommandes();
        return ResponseEntity.ok(commandes);
    }

    @PutMapping("/commandes/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<CommandeDTO> updateCommande(@PathVariable Long id, @RequestBody UpdateCommandeRequest request) {
        CommandeDTO updatedCommande = commandeService.updateCommande(id, request);
        return ResponseEntity.ok(updatedCommande);
    }

    @DeleteMapping("/commandes/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/commandes/{commandeId}/lignes")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<List<LigneCommandeDTO>> getLigneCommandesByCommandeId(@PathVariable Long commandeId) {
        List<LigneCommandeDTO> ligneCommandes = ligneCommandeService.findAllByCommandeId(commandeId);
        return ResponseEntity.ok(ligneCommandes);
    }

    @PutMapping("/lignes/{ligneCommandeId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<LigneCommandeDTO> updateLigneCommande(@PathVariable Long ligneCommandeId, @RequestBody UpdateLigneCommandeRequest request) {
        LigneCommandeDTO updatedLigneCommande = ligneCommandeService.updateLigneCommande(ligneCommandeId, request);
        return ResponseEntity.ok(updatedLigneCommande);
    }

    @DeleteMapping("/lignes/{ligneCommandeId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<Void> deleteLigneCommande(@PathVariable Long ligneCommandeId) {
        ligneCommandeService.deleteLigneCommande(ligneCommandeId);
        return ResponseEntity.noContent().build();
    }
}
