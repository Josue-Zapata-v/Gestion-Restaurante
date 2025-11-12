package com.tecsup.Eval_S12.repository;

import com.tecsup.Eval_S12.entity.Tables;
import com.tecsup.Eval_S12.entity.Tables.TableStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TableRepository extends JpaRepository<Tables, Long> {

    List<Tables> findByStatus(TableStatus status);
}