package com.example.one_more_language.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer port;
    private String type;

    @ManyToOne
    private Namespace namespace;

}
