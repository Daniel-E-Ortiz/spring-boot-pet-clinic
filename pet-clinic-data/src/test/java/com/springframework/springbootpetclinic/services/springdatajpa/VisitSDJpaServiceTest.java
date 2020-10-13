package com.springframework.springbootpetclinic.services.springdatajpa;

import com.springframework.springbootpetclinic.model.Owner;
import com.springframework.springbootpetclinic.model.Pet;
import com.springframework.springbootpetclinic.model.PetType;
import com.springframework.springbootpetclinic.model.Visit;
import com.springframework.springbootpetclinic.repositories.VisitRepository;
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
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    Visit visit1;
    Visit visit2;
    Pet pet1;
    Pet pet2;
    PetType petType1;
    PetType petType2;
    Owner owner1;
    Owner owner2;

    @BeforeEach
    void setUp() {
        owner1 = Owner.ownerBuilder().firstName("Daniel").lastName("Ortiz")
                .address("1415 Moss Creek Dr").city("Leander").telephone("555-555-5555").id(1L).build();
        owner2 = Owner.ownerBuilder().firstName("Amber").lastName("Ortiz")
                .address("1415 Moss Creek Dr").city("Leander").telephone("111-111-1111").id(2L).build();
        petType1 = PetType.petTypeBuilder().id(1L).name("Dog").build();
        petType2 = PetType.petTypeBuilder().id(2L).name("Cat").build();

        pet1 = Pet.petBuilder().id(1L).name("Stella").petType(petType1).owner(owner1).birthDate(LocalDate.now()).build();
        pet2 = Pet.petBuilder().id(2L).name("Dobby").petType(petType2).owner(owner2).birthDate(LocalDate.now()).build();

        owner1.setPets(Set.of(pet1));
        owner2.setPets(Set.of(pet2));

        visit1 = Visit.visitBuilder().date(LocalDate.now()).description("Check-up").pet(pet1).id(1L).build();
        visit2 = Visit.visitBuilder().date(LocalDate.now()).description("Vaccinations").pet(pet2).id(2L).build();

    }

    @Test
    void findAll() {
        Set<Visit> visits = new HashSet<>();
        visits.add(visit1);
        visits.add(visit2);

        when(visitRepository.findAll()).thenReturn(visits);

        Set<Visit> returnedVisits = service.findAll();

        verify(visitRepository).findAll();

        assertNotNull(returnedVisits);
        assertEquals(2, returnedVisits.size());
        assertTrue(returnedVisits.stream().anyMatch(e -> e.getPet().getName() == "Stella"));
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit1));

        Visit returnedVisit = service.findById(1L);

        verify(visitRepository).findById(anyLong());

        assertNotNull(returnedVisit);
        assertEquals(1L, returnedVisit.getId());
    }

    @Test
    void save() {
        when(visitRepository.save(any())).thenReturn(visit1);

        Visit returnedVisit = service.save(visit1);

        verify(visitRepository).save(any());

        assertNotNull(returnedVisit);
    }

    @Test
    void delete() {
        service.delete(visit1);
        verify(visitRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(visitRepository).deleteById(anyLong());
    }
}