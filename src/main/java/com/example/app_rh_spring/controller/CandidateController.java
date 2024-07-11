package com.example.app_rh_spring.controller;

import com.example.app_rh_spring.dto.CandidateDtoGet;
import com.example.app_rh_spring.dto.CandidateDtoPost;
import com.example.app_rh_spring.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/")
    public ResponseEntity<List<CandidateDtoGet>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateDtoGet> getCandidateById(@PathVariable("id") int id) {
        return ResponseEntity.ok(candidateService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<CandidateDtoGet> postNewCandidate(@Validated @RequestBody CandidateDtoPost candidateDtoPost) {
        return ResponseEntity.ok(candidateService.create(candidateDtoPost));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CandidateDtoGet> putCandidate(@PathVariable("id") int id, @Validated @RequestBody CandidateDtoPost candidateDtoPost) {
        return ResponseEntity.ok(candidateService.update(id, candidateDtoPost));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCandidate(@PathVariable("id") int id) {
        return ResponseEntity.ok(candidateService.deleteById(id));
    }
}
