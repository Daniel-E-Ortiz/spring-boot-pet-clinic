package com.springframework.springbootpetclinic.repositories;

import com.springframework.springbootpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType,Long> {
}
