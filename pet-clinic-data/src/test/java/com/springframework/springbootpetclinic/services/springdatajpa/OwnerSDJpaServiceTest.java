package com.springframework.springbootpetclinic.services.springdatajpa;

import com.springframework.springbootpetclinic.model.Owner;
import com.springframework.springbootpetclinic.repositories.OwnerRepository;
import com.springframework.springbootpetclinic.repositories.PetRepository;
import com.springframework.springbootpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner returnedOwner;

    final Long ID = 1L;
    final String LAST_NAME = "Smith";

    @BeforeEach
    void setUp(){
        returnedOwner = Owner.builder().id(ID).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnedOwner);
        Owner smith = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME,smith.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().id(2L).build());
        ownerSet.add(Owner.builder().id(3L).build());

        when(ownerRepository.findAll()).thenReturn(ownerSet);

        Set<Owner> returnedSet = service.findAll();

        assertNotNull(returnedSet);
        assertEquals(2,returnedSet.size());

    }


    @Test
    void findById() {
        when(ownerRepository.findById(anyLong()))
                .thenReturn(Optional.of(returnedOwner));
        Owner owner = service.findById(ID);

        assertNotNull(owner);
        verify(ownerRepository).findById(any());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        Owner owner = service.findById(ID);

        assertNull(owner);
    }

    @Test
    void save() {
        Owner testOwnerToSave = Owner.builder().id(ID).lastName(LAST_NAME).build();

        when(ownerRepository.save(any()))
                .thenReturn(returnedOwner);

        Owner savedOwner = service.save(testOwnerToSave);

        assertNotNull(savedOwner);

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnedOwner);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);
        verify(ownerRepository).deleteById(anyLong());
    }
}