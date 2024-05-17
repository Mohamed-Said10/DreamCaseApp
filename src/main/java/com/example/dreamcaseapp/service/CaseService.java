package com.example.dreamcaseapp.service;

import com.example.dreamcaseapp.exception.CaseNotFoundException;
import com.example.dreamcaseapp.model.Case;
import com.example.dreamcaseapp.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CaseService {

    @Autowired
    private CaseRepository caseRepository;

    public List<Case> getAllCases() {
        return caseRepository.findAll();
    }

    public Case getCaseById(Long caseId) {
        return caseRepository.findById(caseId).orElse(null);
    }

    public Case createCase(Case newCase) {
        newCase.setCreationDate(LocalDateTime.now());
        return caseRepository.save(newCase);
    }

    public Case updateCase(Long caseId, Case updatedCase) {
        Optional<Case> optionalCase = caseRepository.findById(caseId);
        if (optionalCase.isPresent()) {
            Case existingCase = optionalCase.get();
            existingCase.setTitle(updatedCase.getTitle());
            existingCase.setDescription(updatedCase.getDescription());
            existingCase.setLastUpdateDate(LocalDateTime.now());
            return caseRepository.save(existingCase);
        } else {
            throw new CaseNotFoundException("Case with ID " + caseId + " not found");
        }
    }

    public void deleteCase(Long caseId) {
        caseRepository.deleteById(caseId);
    }
}
