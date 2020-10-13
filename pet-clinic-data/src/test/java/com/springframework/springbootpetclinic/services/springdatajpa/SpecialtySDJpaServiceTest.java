package com.springframework.springbootpetclinic.services.springdatajpa;

import com.springframework.springbootpetclinic.model.Specialty;
import com.springframework.springbootpetclinic.repositories.SpecialtyRepository;
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
class SpecialtySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialtySDJpaService service;

    Specialty specialty1;
    Specialty specialty2;

    @BeforeEach
    void setUp() {
        specialty1 = Specialty.specialtyBuilder().id(1L).description("Test1").build();
        specialty2 = Specialty.specialtyBuilder().id(2L).description("Test2").build();
    }

    @Test
    void findAll() {
        Set<Specialty> specialties = new HashSet<>();
        specialties.add(specialty1);
        specialties.add(specialty2);

        when(specialtyRepository.findAll()).thenReturn(specialties);

        Set<Specialty> returnedSet = service.findAll();

        verify(specialtyRepository).findAll();

        assertNotNull(returnedSet);
        assertEquals(2, returnedSet.size());
    }

    @Test
    void findById() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(specialty1));

        Specialty returnedSpecialty = service.findById(1L);

        verify(specialtyRepository).findById(anyLong());

        assertNotNull(returnedSpecialty);
        assertEquals(1L, returnedSpecialty.getId());
    }

    @Test
    void save() {
        when(specialtyRepository.save(any())).thenReturn(specialty1);
        Specialty returnedSpecialty = service.save(specialty1);

        verify(specialtyRepository).save(any());

        assertNotNull(returnedSpecialty);
        assertEquals(1L, returnedSpecialty.getId());
    }

    @Test
    void delete() {
        service.delete(specialty1);
        verify(specialtyRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(specialtyRepository).deleteById(anyLong());
    }
}