package com.example.userservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "typeofreports")
public class ReportTypesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private int typeId;
    @Column(name = "type", unique = true)
    private String reportType;
    @Column(name = "action")
    private String reportAction;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "reportType")
    private Set<ReportEntity> reports = new HashSet<>();
}
