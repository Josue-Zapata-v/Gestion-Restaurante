package com.tecsup.Eval_S12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tecsup.Eval_S12.service.AuditService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AuditService auditService;

    public AdminController(AuditService auditService) {
        this.auditService = auditService;
    }


    @GetMapping("/bitacora")
    public String listBitacoraRecords(Model model, HttpServletRequest request) {
        model.addAttribute("registros", auditService.listBitacoraRecords());
        model.addAttribute("currentPath", request.getRequestURI());
        return "admin/list_bitacora";
    }

}