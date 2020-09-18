package com.springframework.springbootpetclinic.repositories;

import com.springframework.springbootpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet,Long> {
}
