package com.example.rateservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "addition")
public class AdditionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private Long additionMaterialId;
    @Column(name = "material_url", nullable = false)
    private String materialUrl;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    private AdditionsTypeEntity additionType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "review_id", referencedColumnName = "review_id")
    private ReviewEntity review;
}
