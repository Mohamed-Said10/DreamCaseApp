package com.example.dreamcaseapp.controller;

import com.example.dreamcaseapp.exception.CaseNotFoundException;
import com.example.dreamcaseapp.model.Case;
import com.example.dreamcaseapp.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cases")
public class CaseController {

    @Autowired
    private CaseService caseService;

    @GetMapping
    public List<Case> getAllCases() {
        return caseService.getAllCases();
    }

    @GetMapping("/{caseId}")
    public ResponseEntity<Case> getCaseById(@PathVariable Long caseId) {
        Case returnedCase = caseService.getCaseById(caseId);
        if (returnedCase != null) {
            return ResponseEntity.ok(returnedCase);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Case createCase(@RequestBody Case newCase) {
        return caseService.createCase(newCase);
    }

    @PutMapping("/{caseId}")
    public ResponseEntity<Case> updateCase(@PathVariable Long caseId, @RequestBody Case updatedCase) {
        try {
            Case updated = caseService.updateCase(caseId, updatedCase);
            return ResponseEntity.ok(updated);
        } catch (CaseNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{caseId}")
    public ResponseEntity<Void> deleteCase(@PathVariable Long caseId) {
        caseService.deleteCase(caseId);
        return ResponseEntity.noContent().build();
    }
}
