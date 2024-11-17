package com.example.back.controller;

import com.example.back.entity.Commercial;
import com.example.back.service.CommercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commercials")
public class CommercialController {

    @Autowired
    private CommercialService commercialService;

    @PostMapping
    public ResponseEntity<Commercial> createCommercial(@RequestBody Commercial commercial) {
        Commercial createdCommercial = commercialService.createCommercial(commercial);
        return new ResponseEntity<>(createdCommercial, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commercial> getCommercialById(@PathVariable int id) {
        Commercial commercial = commercialService.getCommercialById(id);
        if (commercial != null) {
            return new ResponseEntity<>(commercial, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commercial> updateCommercial(
            @PathVariable int id,
            @RequestBody Commercial updatedCommercial
    ) {
        Commercial commercial = commercialService.updateCommercial(id, updatedCommercial);
        if (commercial != null) {
            return new ResponseEntity<>(commercial, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommercial(@PathVariable int id) {
        if (commercialService.getCommercialById(id) != null) {
            commercialService.deleteCommercial(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Commercial>> getAllCommercials() {
        List<Commercial> commercials = commercialService.getAllCommercials();
        return new ResponseEntity<>(commercials, HttpStatus.OK);
    }
}
