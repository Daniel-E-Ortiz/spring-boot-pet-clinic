package com.springframework.springbootpetclinic.bootstrap;

import com.springframework.springbootpetclinic.model.Owner;
import com.springframework.springbootpetclinic.model.Vet;
import com.springframework.springbootpetclinic.services.OwnerService;
import com.springframework.springbootpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataInit(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Daniel");
        owner1.setLastName("Ortiz");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Amber");
        owner2.setLastName("Ortiz");

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Matt");
        vet1.setLastName("Damon");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Daniel");
        vet2.setLastName("Riccardo");

        vetService.save(vet2);

        System.out.println("Loaded Vets....");

    }
}
