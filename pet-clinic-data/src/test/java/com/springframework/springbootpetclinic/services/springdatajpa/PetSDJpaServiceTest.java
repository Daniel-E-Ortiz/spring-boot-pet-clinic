package com.springframework.springbootpetclinic.services.springdatajpa;

import com.springframework.springbootpetclinic.model.Owner;
import com.springframework.springbootpetclinic.model.Pet;
import com.springframework.springbootpetclinic.model.PetType;
import com.springframework.springbootpetclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaService service;

    Pet pet1;
    Pet pet2;

    @BeforeEach
    void setUp() {
        pet1 = Pet.builder()
                .id(1L)
                .name("Stella")
                .petType(PetType.builder().name("Dog").build())
                .birthDate(LocalDate.now())
                .owner(Owner.builder().id(3L).lastName("DogOwner").build())
                .build();
        pet2 = Pet.builder()
                .id(2L)
                .name("Baxter")
                .petType(PetType.builder().name("Cat").build())
                .birthDate(LocalDate.now())
                .owner(Owner.builder().id(4L).lastName("CatOwner").build())
                .build();
    }

    @Test
    void findAll() {
        Set<Pet> testPetSet = new HashSet<>();
        testPetSet.add(pet1);
        testPetSet.add(pet2);

        when(petRepository.findAll()).thenReturn(testPetSet);

        Set<Pet> returnedPets = service.findAll();

        assertNotNull(returnedPets);
        assertEquals(2,returnedPets.size());

        verify(petRepository).findAll();
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong()))
                .thenReturn(Optional.of(pet1));
        Pet returnedPet = service.findById(1L);

        assertEquals("Stella", returnedPet.getName());
        assertNotNull(returnedPet);

        verify(petRepository).findById(anyLong());
    }

    @Test
    void findByIdNotExist() {
        when(petRepository.findById(9L))
                .thenReturn(Optional.empty());
        Pet returnedPet = service.findById(9L);

        assertNull(returnedPet);

        verify(petRepository).findById(anyLong());
    }

    @Test
    void save() {

        when(petRepository.save(any())).thenReturn(pet1);

        Pet returnedPet = service.save(pet1);

        assertNotNull(returnedPet);
        assertEquals(pet1.getId(),returnedPet.getId());
        assertEquals(1L, returnedPet.getId());

        verify(petRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(pet1);
        verify(petRepository).delete(pet1);
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(petRepository).deleteById(1L);
    }
}