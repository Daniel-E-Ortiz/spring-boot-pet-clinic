package com.springframework.springbootpetclinic.services.map;

import com.springframework.springbootpetclinic.model.Speciality;
import com.springframework.springbootpetclinic.services.SpecialtiesService;

import java.util.Set;

public class SpecialtiesMapService extends AbstractMapService<Speciality, Long> implements SpecialtiesService {

    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Speciality speciality) {
        super.delete(speciality);
    }

    @Override
    public Speciality save(Speciality speciality) {
        return super.save(speciality);
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }
}
