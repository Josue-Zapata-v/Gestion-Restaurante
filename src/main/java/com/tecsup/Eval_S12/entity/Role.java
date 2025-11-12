package com.tecsup.Eval_S12.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Define los roles exactos: ADMIN, MOZO, COCINERO, CAJERO
    @Column(unique = true, nullable = false)
    private String name;
}