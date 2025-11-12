package com.tecsup.Eval_S12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tecsup.Eval_S12.service.AuditService;
import jakarta.servlet.http.HttpServletRequest; // 👈 1. Importar

@Controller
@RequestMapping("/admin") // Ruta protegida solo para ADMIN
public class AdminController {

    // Inyectamos el servicio de Auditoría
    private final AuditService auditService;

    public AdminController(AuditService auditService) {
        this.auditService = auditService;
    }

    /**
     * Muestra el listado completo de la Bitácora.
     */
    @GetMapping("/bitacora")
    public String listBitacoraRecords(Model model, HttpServletRequest request) { // 👈 2. Agregar HttpServletRequest
        model.addAttribute("registros", auditService.listBitacoraRecords());
        model.addAttribute("currentPath", request.getRequestURI()); // 👈 3. Pasar la URL al modelo
        return "admin/list_bitacora";
    }

}