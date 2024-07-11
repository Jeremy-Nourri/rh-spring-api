package com.example.app_rh_spring.service;

import com.example.app_rh_spring.dto.CandidateDtoGet;
import com.example.app_rh_spring.dto.CandidateDtoPost;
import com.example.app_rh_spring.entity.Candidate;
import com.example.app_rh_spring.exception.NotFoundException;
import com.example.app_rh_spring.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CandidateDtoGet convertToDto(Candidate candidate) {
        return CandidateDtoGet.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .identificationNumber(candidate.getIdentificationNumber())
                .address(candidate.getAddress())
                .phone(candidate.getPhone())
                .email(candidate.getEmail())
                .birthDate(candidate.getBirthDate())
                .rating(candidate.getRating())
                .observation(candidate.getObservation())
                .skill(candidate.getSkill())
                .technicalArea(candidate.getTechnicalArea())
                .jobInterviewDate(candidate.getJobInterviewDate())
                .build();
    }

    public CandidateDtoGet create(CandidateDtoPost candidateDtoPost) {
       Candidate candidate = Candidate.builder()
                .name(candidateDtoPost.getName())
                .identificationNumber(candidateDtoPost.getIdentificationNumber())
                .address(candidateDtoPost.getAddress())
                .phone(candidateDtoPost.getPhone())
                .email(candidateDtoPost.getEmail())
                .birthDate(LocalDate.parse(candidateDtoPost.getBirthDate(), dateFormatter))
                .rating(candidateDtoPost.getRating())
                .observation(candidateDtoPost.getObservation())
                .skill(candidateDtoPost.getSkill())
                .technicalArea(candidateDtoPost.getTechnicalArea())
                .jobInterviewDate(LocalDate.parse(candidateDtoPost.getJobInterviewDate(), dateFormatter))
                .build();
       candidateRepository.save(candidate);
       return convertToDto(candidate);

    }

    public CandidateDtoGet findById(int id) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow();
        return convertToDto(candidate);
    }

    public boolean deleteById(int id) {
        Candidate candidateFound = candidateRepository.findById(id).orElseThrow(NotFoundException::new);
        candidateRepository.delete(candidateFound);
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        return candidate == null;
    }

    public CandidateDtoGet update(int id, CandidateDtoPost candidateDtoPost) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(NotFoundException::new);
        candidate.setName(candidateDtoPost.getName());
        candidate.setIdentificationNumber(candidateDtoPost.getIdentificationNumber());
        candidate.setAddress(candidateDtoPost.getAddress());
        candidate.setPhone(candidateDtoPost.getPhone());
        candidate.setEmail(candidateDtoPost.getEmail());
        candidate.setBirthDate(LocalDate.parse(candidateDtoPost.getBirthDate(), dateFormatter));
        candidate.setRating(candidateDtoPost.getRating());
        candidate.setObservation(candidateDtoPost.getObservation());
        candidate.setSkill(candidateDtoPost.getSkill());
        candidate.setTechnicalArea(candidateDtoPost.getTechnicalArea());
        candidate.setJobInterviewDate(LocalDate.parse(candidateDtoPost.getJobInterviewDate(), dateFormatter));
        candidateRepository.save(candidate);
        return convertToDto(candidate);
    }

    public List<CandidateDtoGet> findAll() {
        List<Candidate> candidates = (List<Candidate>) candidateRepository.findAll();
        return candidates.stream().map(this::convertToDto).toList();
    }

}
