package savvy.controllers;


import _Users_maria_Desktop_spring_boot_example_target_generated.CandidateApi;
import _Users_maria_Desktop_spring_boot_example_target_generated.CandidateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import savvy.entities.Candidate;
import savvy.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CandidateController implements CandidateApi {

    @Autowired
    UserService userService;



    public ResponseEntity<CandidateDto> getUserById(String id) {
        return userService.getCandidateById(id)
                .map(candidate -> ResponseEntity.ok(new CandidateDto().id(candidate.getId())))
                .orElseThrow(() -> new NullPointerException("test"));
    }

    @Override
    public ResponseEntity<CandidateDto> addCandidate(CandidateDto candidateDto) {
        return Optional.ofNullable(candidateDto)
                .map(dto -> {
                    Candidate candidate = userService.saveCandidate(dto); // Assuming userService.addCandidate saves and returns the entity
                    return ResponseEntity.ok(new CandidateDto().id(candidate.getId()));
                })
                .orElseThrow(() -> new IllegalArgumentException("Error: Candidate data cannot be null."));
        //return CandidateApi.super.addCandidate(candidateDto);
    }

    @Override
    public ResponseEntity<String> deleteCandidate(String id) {
        return userService.deleteCandidate(id);
    }

    @Override
    public ResponseEntity<List<CandidateDto>> getAllCandidates() {
        return Optional.ofNullable(userService.getAllCandidates())
                .filter(candidates -> !candidates.isEmpty())
                .map(candidates -> ResponseEntity.ok(
                        candidates.stream()
                                .map(candidate -> new CandidateDto().id(candidate.getId()))
                                .toList()
                ))
                .orElseThrow(() -> new NoSuchElementException("Error: No candidates found."));
    }

    @Override
    public ResponseEntity<CandidateDto> getCandidateById(String id) {
        return userService.getCandidateById(id)
                .map(candidate -> ResponseEntity.ok(new CandidateDto().id(candidate.getId())))
                .orElseThrow(() -> new NoSuchElementException("Error: No candidate found."));
    }

    @Override
    public ResponseEntity<CandidateDto> updateCandidate(String id, CandidateDto candidateDto) {
        return Optional.ofNullable(candidateDto)
                .flatMap(dto -> Optional.ofNullable(userService.updateCandidate(id, dto)))
                .map(updatedCandidate -> ResponseEntity.ok(new CandidateDto().id(updatedCandidate.getId())))
                .orElseThrow(() -> new NoSuchElementException("Error: Candidate with ID " + id + " not found or could not be updated."));
        //return CandidateApi.super.updateCandidate(id, candidateDto);
    }

    @Override
    public ResponseEntity<String> uploadPDF(String candidateId, MultipartFile file) {
        return Optional.ofNullable(file)
                .filter(uploadedFile -> !uploadedFile.isEmpty())
                .map(uploadedFile -> {
                    userService.uploadPDF(candidateId, uploadedFile); // Assuming this saves the file
                    return ResponseEntity.ok("PDF uploaded successfully for Candidate with ID " + candidateId);
                })
                .orElseThrow(() -> new IllegalArgumentException("Error: File cannot be null or empty."));

        //return CandidateApi.super.uploadPDF(candidateId, file);
    }
}
