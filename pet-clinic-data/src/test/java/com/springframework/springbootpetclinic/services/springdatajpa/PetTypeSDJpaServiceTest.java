package com.springframework.springbootpetclinic.services.springdatajpa;

import com.springframework.springbootpetclinic.model.PetType;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetTypeSDJpaServiceTest {

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeSDJpaService service;

    PetType dogPetType;
    PetType catPetType;

    @BeforeEach
    void setUp() {
        dogPetType = PetType.petTypeBuilder().id(1L).name("Dog").build();
        catPetType = PetType.petTypeBuilder().id(2L).name("Cat").build();
    }


    @Test
    void findAll() {
        Set<PetType> petTypeSet = new HashSet<>();
        petTypeSet.add(dogPetType);
        petTypeSet.add(catPetType);

        when(petTypeRepository.findAll()).thenReturn(petTypeSet);

        Set<PetType> returnedPetTypes = service.findAll();

        verify(petTypeRepository).findAll();

        assertEquals(2, returnedPetTypes.size());
        assertNotNull(returnedPetTypes);
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(dogPetType));
        PetType returnedPetType = service.findById(1L);

        verify(petTypeRepository).findById(anyLong());

        assertNotNull(returnedPetType);
        assertEquals(1L, returnedPetType.getId());
    }

    @Test
    void save() {
        PetType petTypeToSave = PetType.petTypeBuilder().id(99L).name("Test").build();

        when(petTypeRepository.save(any())).thenReturn(dogPetType);

        PetType returnedPetType = service.save(petTypeToSave);

        verify(petTypeRepository).save(any());

        assertNotNull(returnedPetType);
        assertEquals(1L, returnedPetType.getId());
    }

    @Test
    void delete() {
        service.delete(dogPetType);
        verify(petTypeRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(petTypeRepository).deleteById(anyLong());
    }
}