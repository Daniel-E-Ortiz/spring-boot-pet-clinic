package com.springframework.springbootpetclinic.bootstrap;

import com.springframework.springbootpetclinic.model.*;
import com.springframework.springbootpetclinic.services.OwnerService;
import com.springframework.springbootpetclinic.services.PetTypeService;
import com.springframework.springbootpetclinic.services.SpecialtyService;
import com.springframework.springbootpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInit implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataInit(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                    SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0){
            loadData();
        }
    }

    private void loadData() {
        System.out.println("Loading new data....");
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);


        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);


        Owner owner1 = new Owner();
        owner1.setFirstName("Daniel");
        owner1.setLastName("Ortiz");
        owner1.setAddress("123 Fakestreet");
        owner1.setCity("Leander");
        owner1.setTelephone("123-456-7890");

        Pet danielsPet = new Pet();
        danielsPet.setPetType(savedDogPetType);
        danielsPet.setOwner(owner1);
        danielsPet.setName("Stella");
        danielsPet.setBirthDate(LocalDate.of(2016,7,5));

        owner1.getPets().add(danielsPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Amber");
        owner2.setLastName("Ortiz");
        owner2.setAddress("456 Blanks Street");
        owner2.setCity("Round Rock");
        owner2.setTelephone("123-098-7654");

        Pet ambersCat = new Pet();
        ambersCat.setName("Derp");
        ambersCat.setBirthDate(LocalDate.now());
        ambersCat.setPetType(savedCatPetType);
        ambersCat.setOwner(owner2);

        owner2.getPets().add(ambersCat);

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Matt");
        vet1.setLastName("Damon");
        vet1.getSpecialties().add(savedRadiology);
        vet1.getSpecialties().add(savedSurgery);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Daniel");
        vet2.setLastName("Riccardo");
        vet2.getSpecialties().add(savedDentistry);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
