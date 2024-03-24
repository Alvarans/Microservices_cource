package com.example.filmservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "verification")
public class VerificationListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_id")
    private int verificationId;
    @Column(name = "verification_text", nullable = false)
    private String verificationText;
    //User
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;
    //Document Type
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id", referencedColumnName = "document_type_id")
    private DocumentTypeEntity documentType;
}
