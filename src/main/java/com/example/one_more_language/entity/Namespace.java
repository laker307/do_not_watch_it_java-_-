package com.example.one_more_language.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Namespace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer resources;
    private String name;
    private String policy;

    @ManyToOne
    private Cluster cluster;
}
