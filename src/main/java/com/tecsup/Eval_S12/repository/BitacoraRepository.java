package com.tecsup.Eval_S12.repository;

import com.tecsup.Eval_S12.entity.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {
    // Métodos de consulta personalizados si son necesarios en el futuro
}