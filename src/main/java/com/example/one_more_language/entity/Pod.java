package com.example.one_more_language.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Pod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String labels;
    private String containers;

    @ManyToOne
    private Namespace namespace;

}
