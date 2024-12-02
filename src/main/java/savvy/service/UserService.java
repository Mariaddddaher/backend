package savvy.service;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import savvy.entities.Candidate;
import savvy.entities.PDFDocument;
import savvy.repository.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Candidate saveCandidate(Candidate candidate) {
        return userRepository.save(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return userRepository.findAll();
    }

    public Optional<Candidate> getCandidateById(String id) {
        return userRepository.findById(id);
    }

    public Candidate updateCandidate(String id, Candidate newCandidate) {
        return userRepository.findById(id).map(candidate -> {
            candidate.setName(newCandidate.getName());
            candidate.setAge(newCandidate.getAge());
            candidate.setAddress(newCandidate.getAddress());
            return userRepository.save(candidate);
        }).orElseThrow(() -> new RuntimeException("Candidate not found with id " + id));
    }

    public void deleteCandidate(String id) {
        userRepository.deleteById(id);
    }

    public Candidate uploadPDFToCandidate(String candidateId, MultipartFile file) throws IOException {
        Candidate candidate;

        candidate = userRepository.findById(String.valueOf(candidateId)).orElseThrow(() -> new RuntimeException("Candidate not found"));

        PDFDocument pdf = new PDFDocument();
        pdf.setContent(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        pdf.setFileName("Test" + candidateId);
        candidate.setPdfDocument(pdf);

        return userRepository.save(candidate);
    }
}

