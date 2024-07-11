package com.example.app_rh_spring.repository;

import com.example.app_rh_spring.entity.Vacation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository extends CrudRepository<Vacation, Integer> {
}
