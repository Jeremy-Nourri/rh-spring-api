package com.example.app_rh_spring.repository;

import com.example.app_rh_spring.entity.Candidate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Integer>{
}
