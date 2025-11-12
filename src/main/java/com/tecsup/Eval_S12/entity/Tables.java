package com.tecsup.Eval_S12.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tables")
public class Tables {

    public enum TableStatus {
        DISPONIBLE, OCUPADA, RESERVADA, MANTENIMIENTO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTable;

    @Column(unique = true, nullable = false)
    private Integer number;

    @Column(nullable = false)
    private Integer capacity;

    // Persistimos el enum como un String
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TableStatus status = TableStatus.DISPONIBLE;
}