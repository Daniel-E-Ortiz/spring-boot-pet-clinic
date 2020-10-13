package com.springframework.springbootpetclinic.services.springdatajpa;

import com.springframework.springbootpetclinic.model.Specialty;
import com.springframework.springbootpetclinic.model.Vet;
import com.springframework.springbootpetclinic.repositories.VetRepository;
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
class VetSDJpaServiceTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetSDJpaService service;

    Vet vet1;
    Vet vet2;
    Specialty specialty1;
    Specialty specialty2;
    Set<Specialty> specialties;

    @BeforeEach
    void setUp() {
        specialty1 = Specialty.specialtyBuilder().id(1L).description("Surgeon").build();
        specialty2 = Specialty.specialtyBuilder().id(2L).description("Anesthesiologist").build();

        specialties = new HashSet<>();
        specialties.add(specialty1);
        specialties.add(specialty2);

        vet1 = Vet.vetBuilder().firstName("Daniel").lastName("Ortiz").specialties(specialties).id(1L).build();
        vet2 = Vet.vetBuilder().firstName("Amber").lastName("Ortiz").specialties(specialties).id(2L).build();
    }

    @Test
    void findAll() {
        Set<Vet> vetSet = new HashSet<>();
        vetSet.add(vet1);
        vetSet.add(vet2);
        when(vetRepository.findAll()).thenReturn(vetSet);

        Set<Vet> returnedSet = service.findAll();

        verify(vetRepository).findAll();

        assertNotNull(returnedSet);
        assertEquals(2, returnedSet.size());
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(vet1));

        Vet returnedVet = service.findById(1L);

        verify(vetRepository).findById(anyLong());

        assertNotNull(returnedVet);
        assertEquals("Daniel", vet1.getFirstName());
        assertTrue(vet1.getSpecialties().stream().anyMatch(e -> e.getDescription() == "Surgeon"));
    }

    @Test
    void save() {
        when(vetRepository.save(any())).thenReturn(vet1);
        Vet returnedVet = service.save(vet1);
        verify(vetRepository).save(any());

        assertNotNull(returnedVet);
        assertEquals(1L, returnedVet.getId());
    }

    @Test
    void delete() {
        service.delete(vet1);
        verify(vetRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(vetRepository).deleteById(anyLong());
    }
}