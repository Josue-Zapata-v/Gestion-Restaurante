package com.tecsup.Eval_S12.service;

import com.tecsup.Eval_S12.entity.Customer;
import com.tecsup.Eval_S12.entity.Tables;
import com.tecsup.Eval_S12.entity.Tables.TableStatus;
import com.tecsup.Eval_S12.repository.TableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TableService {

    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<Tables> listAllTables() {
        return tableRepository.findAll();
    }

    public List<Tables> listAvailableTables() {
        return tableRepository.findByStatus(TableStatus.DISPONIBLE);
    }

    public Optional<Tables> findTableById(Long id) {
        return tableRepository.findById(id);
    }

    @Transactional
    public Tables changeTableState(Long tableId, TableStatus newState) {
        Tables tables = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Tables not found with ID: " + tableId));

        tables.setStatus(newState);
        return tableRepository.save(tables);
    }

    @Transactional
    public void loadDemoTables() {
        if (tableRepository.count() == 0) {
            tableRepository.save(new Tables(null, 1, 4, TableStatus.DISPONIBLE));
            tableRepository.save(new Tables(null, 2, 2, TableStatus.DISPONIBLE));
            tableRepository.save(new Tables(null, 3, 6, TableStatus.DISPONIBLE));
            tableRepository.save(new Tables(null, 4, 8, TableStatus.DISPONIBLE));
            tableRepository.save(new Tables(null, 5, 2, TableStatus.DISPONIBLE));
        }
    }
    @Transactional
    public Tables assignTableToCustomer(Long idTable, Customer customer) {
        // Asegúrate de que TableRepository esté inyectado y use la entidad Tables.
        Tables table = tableRepository.findById(idTable)
                .orElseThrow(() -> new RuntimeException("Mesa ID " + idTable + " no encontrada."));

        // 2. Corregido el Enum: 'TablseStatus.AVAILABLE' -> 'Tables.TableStatus.DISPONIBLE'
        // Asumo que tu Enum está en español y anidado.
        if (table.getStatus() != Tables.TableStatus.DISPONIBLE) {
            throw new RuntimeException("La mesa " + table.getNumber() + " no está disponible para asignación.");
        }

        // 3. Corregido el Enum: 'TableStatus.OCCUPIED' -> 'Tables.TableStatus.OCUPADA'
        table.setStatus(Tables.TableStatus.OCUPADA);

        // ... (Comentarios de lógica simplificada se mantienen) ...

        return tableRepository.save(table);
    }
}