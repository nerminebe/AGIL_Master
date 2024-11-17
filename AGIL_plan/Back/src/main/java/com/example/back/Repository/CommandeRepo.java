package com.example.back.repositories;
import com.example.back.entity.Commande;
import com.example.back.entity.Intervenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepo extends JpaRepository<Commande, Long>  {


    List<Commande> findByIntervenant(Intervenant intervenant);

    List<Commande> findByGerantId(int gerantId);

}
