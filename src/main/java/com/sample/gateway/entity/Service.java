package com.sample.gateway.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "services")
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @ManyToOne
    private System system;

    @ManyToMany
    @JoinTable(name = "service_form_field",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "form_field_id"))
    private Set<FormField> fields;

    private static final long serialVersionUID = 1L;
}
