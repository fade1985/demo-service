package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "model")
@Data
@Builder
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String webpage;

    @Column(nullable = false)
    private int pictures;

    @Column(nullable = false)
    private int videos;
}
