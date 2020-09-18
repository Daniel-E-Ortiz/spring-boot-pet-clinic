package com.springframework.springbootpetclinic.repositories;

import com.springframework.springbootpetclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty,Long> {
}
