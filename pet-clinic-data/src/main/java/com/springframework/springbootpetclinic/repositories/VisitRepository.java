package com.springframework.springbootpetclinic.repositories;

import com.springframework.springbootpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
