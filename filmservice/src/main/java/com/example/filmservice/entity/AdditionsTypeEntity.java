package com.example.filmservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "additionstype")
public class AdditionsTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private int additionsTypeId;
    @Column(name = "type", unique = true, nullable = false)
    private String additionsTypeName;
    //Additions
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "additionType")
    private Set<AdditionEntity> additions = new HashSet<>();
}
