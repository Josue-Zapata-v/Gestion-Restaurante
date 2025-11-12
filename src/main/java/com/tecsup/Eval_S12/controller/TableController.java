package com.tecsup.Eval_S12.controller;

import com.tecsup.Eval_S12.entity.Tables.TableStatus;
import com.tecsup.Eval_S12.service.TableService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customers/tables")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
        tableService.loadDemoTables();
    }

    @GetMapping("")
    public String listTables(Model model, HttpServletRequest request) {
        model.addAttribute("tables", tableService.listAllTables());
        model.addAttribute("currentPath", request.getRequestURI());
        return "table/list_tables";
    }

    @PostMapping("/state/{idTable}/{newState}")
    public String changeTableState(@PathVariable Long idTable,
                                   @PathVariable TableStatus newState,
                                   RedirectAttributes ra) {
        try {
            tableService.changeTableState(idTable, newState);
            ra.addFlashAttribute("success", "Table ID " + idTable + " updated to " + newState.name() + ".");
        } catch (RuntimeException e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/customers/tables";
    }
}
