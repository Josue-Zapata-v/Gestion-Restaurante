package com.tecsup.Eval_S12.service;

import com.tecsup.Eval_S12.entity.Bitacora;
import com.tecsup.Eval_S12.repository.BitacoraRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditService {

    private final BitacoraRepository bitacoraRepository;

    public AuditService(BitacoraRepository bitacoraRepository) {
        this.bitacoraRepository = bitacoraRepository;
    }

    public List<Bitacora> listBitacoraRecords() {
        return bitacoraRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaHora"));
    }
}