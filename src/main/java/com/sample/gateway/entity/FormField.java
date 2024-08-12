package com.sample.gateway.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "form_fields", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"field_name", "field_label"})
})
public class FormField implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "field_label")
    private String fieldLabel;

    @Column(name = "mandatory")
    private Boolean mandatory;

    @ManyToMany(mappedBy = "fields")
    private Set<Service> services;

    private static final long serialVersionUID = 1L;
}
