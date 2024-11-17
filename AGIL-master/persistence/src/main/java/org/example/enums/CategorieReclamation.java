package org.example.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CategorieReclamation {

    // RT
    PISTE(TypeReclamation.TECHNIQUE, "Problèmes liés à la piste"),
    DISTRIBUTEUR(TypeReclamation.TECHNIQUE, "Problèmes liés au distributeur"),
    CITERNE(TypeReclamation.TECHNIQUE, "Problèmes liés à la citerne"),
    NICHE_DEPOTAGE(TypeReclamation.TECHNIQUE, "Problèmes liés à la niche de dépotage"),
    MANOMETRE(TypeReclamation.TECHNIQUE, "Problèmes liés au manomètre"),
    ECLAIRAGE(TypeReclamation.TECHNIQUE, "Problèmes d'éclairage"),
    PISTE_DEGRADEE(TypeReclamation.TECHNIQUE, "Piste dégradée"),
    CONDUITES_FUYARDES(TypeReclamation.TECHNIQUE, "Conduites fuyardes"),
    EQUIPEMENT_SECURITE(TypeReclamation.TECHNIQUE, "Problèmes d'équipements de sécurité"),
    COFFRET_TGBT(TypeReclamation.TECHNIQUE, "Problèmes de coffret TGBT"),
    MARQUISE_ENDOMMAGEE(TypeReclamation.TECHNIQUE, "Marquise endommagée"),
    SIGNALISATION_ENDOMMAGEE(TypeReclamation.TECHNIQUE, "Signalisation endommagée"),
    AUTRES_TECHNIQUES(TypeReclamation.TECHNIQUE, "Autres réclamations techniques"),

    // RC
    CERTIFICAT_ETANCHEITE(TypeReclamation.COMMERCIALE, "Certificat d'étanchéité"),
    RETARD_LIVRAISON(TypeReclamation.COMMERCIALE, "Retard de livraison"),
    ROULEAUX_TPE(TypeReclamation.COMMERCIALE, "Rouleaux TPE"),
    SACS_BORDEREAUX_GERANTS(TypeReclamation.COMMERCIALE, "Sacs bordereaux gérants"),
    PRODUIT_NON_CONFORME(TypeReclamation.COMMERCIALE, "Produit non conforme"),
    AUTRES_COMMERCIALES(TypeReclamation.COMMERCIALE, "Autres réclamations commerciales");

    private final TypeReclamation type;
    private final String description;

    CategorieReclamation(TypeReclamation type, String description) {
        this.type = type;
        this.description = description;
    }

    public static List<CategorieReclamation> getCategoriesByType(TypeReclamation type) {
        return Stream.of(CategorieReclamation.values())
                .filter(categorie -> categorie.getType().equals(type))
                .collect(Collectors.toList());
    }

    public TypeReclamation getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

}
