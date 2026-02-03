package com.example.babylog.controller;

import com.example.babylog.model.Baby;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/babies")
public class BabyController {

    private final Map<String, Baby> babyProfiles = new HashMap<>();

    @PostMapping
    public ResponseEntity<Baby> createBabyProfile(@RequestBody Baby baby) {
        String babyId = UUID.randomUUID().toString();
        baby.setId(babyId);
        babyProfiles.put(babyId, baby);
        return ResponseEntity.status(HttpStatus.CREATED).body(baby);
    }

    @PostMapping("/{babyId}/events")
    public ResponseEntity<String> addEvents(@PathVariable String babyId, @RequestBody Map<String, Object> events) {
        if (!babyProfiles.containsKey(babyId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Baby profile not found");
        }
        // Logic to handle events can be added here
        return ResponseEntity.status(HttpStatus.CREATED).body("Events added for baby: " + babyId);
    }
}