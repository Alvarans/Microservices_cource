package com.example.filmservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "documenttype")
public class DocumentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_type_id")
    private int documentTypeId;
    @Column(name = "document_name", unique = true, nullable = false)
    private String documentName;
    //Verification
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "documentType")
    @Column(name = "verification_list")
    Set<VerificationListEntity> verificationList = new HashSet<>();
}
