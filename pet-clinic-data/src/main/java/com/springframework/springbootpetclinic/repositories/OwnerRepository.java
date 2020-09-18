package com.springframework.springbootpetclinic.repositories;

import com.springframework.springbootpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner,Long> {
}
