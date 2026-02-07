package com.example.babylog.controller;

import com.example.babylog.model.Baby;
import com.example.babylog.repository.BabyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/babies")
public class BabyController {

    @Autowired
    private BabyRepository babyRepository;

    @PostMapping
    public ResponseEntity<Baby> createBabyProfile(@RequestBody Baby baby) {
        Baby savedBaby = babyRepository.save(baby);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBaby);
    }

    @GetMapping("/{babyId}")
    public ResponseEntity<Baby> getBabyProfile(@PathVariable String babyId) {
        return babyRepository.findById(babyId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{babyId}/events")
    public ResponseEntity<String> addEvents(@PathVariable String babyId, @RequestBody List<String> events) {
        return babyRepository.findById(babyId).map(baby -> {
            if (baby.getEvents() == null) {
                baby.setEvents(new ArrayList<>());
            }
            baby.getEvents().addAll(events);
            babyRepository.save(baby);
            return ResponseEntity.status(HttpStatus.CREATED).body("Events added for baby: " + babyId);
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Baby profile not found"));
    }

    @GetMapping("/{babyId}/events")
    public ResponseEntity<List<String>> getEvents(@PathVariable String babyId) {
        return babyRepository.findById(babyId)
                .map(baby -> ResponseEntity.ok(baby.getEvents()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{babyId}")
    public ResponseEntity<Void> deleteBabyProfile(@PathVariable String babyId) {
        return babyRepository.findById(babyId).map(baby -> {
            babyRepository.delete(baby);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
