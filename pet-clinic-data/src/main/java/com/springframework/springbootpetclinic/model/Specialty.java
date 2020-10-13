package com.springframework.springbootpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specialties")
public class Specialty extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Builder(builderMethodName = "specialtyBuilder")
    public Specialty(Long id, String description){
        super(id);
        this.description = description;
    }
}
