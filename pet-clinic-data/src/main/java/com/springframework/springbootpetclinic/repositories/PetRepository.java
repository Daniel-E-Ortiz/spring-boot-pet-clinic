package com.springframework.springbootpetclinic.repositories;

import com.springframework.springbootpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet,Long> {
}
