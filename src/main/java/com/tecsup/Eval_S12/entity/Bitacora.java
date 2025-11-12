package com.tecsup.Eval_S12.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bitacora")
public class Bitacora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBitacora;

    @Column(nullable = false)
    private String nombreUsuario;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String accion;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    public Bitacora(String nombreUsuario, String accion, LocalDateTime fechaHora) {
        this.nombreUsuario = nombreUsuario;
        this.accion = accion;
        this.fechaHora = fechaHora;
    }
}