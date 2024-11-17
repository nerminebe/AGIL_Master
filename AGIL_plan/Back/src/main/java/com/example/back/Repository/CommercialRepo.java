package com.example.back.repositories;

import com.example.back.entity.Commercial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommercialRepo extends JpaRepository<Commercial, Long> {

}
