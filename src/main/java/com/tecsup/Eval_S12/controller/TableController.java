package com.tecsup.Eval_S12.controller;

import com.tecsup.Eval_S12.entity.Customer;
import com.tecsup.Eval_S12.entity.Tables.TableStatus;
import com.tecsup.Eval_S12.service.TableService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import com.tecsup.Eval_S12.service.CustomerService; // 👈 Importar
import com.tecsup.Eval_S12.entity.Customer; // 👈 Importar
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customers/tables")
public class TableController {

    private final TableService tableService;
    private final CustomerService customerService; // 👈 Inyección

    public TableController(TableService tableService, CustomerService customerService) {
        this.tableService = tableService;
        this.customerService = customerService; // Asignación
        tableService.loadDemoTables();
    }

    @GetMapping("")
    public String listTables(Model model, HttpServletRequest request) {
        model.addAttribute("tables", tableService.listAllTables());
        model.addAttribute("customer", new Customer());
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

    @PostMapping("/assign/{idCustomer}/{idTable}")
    public String assignTable(@PathVariable Long idCustomer,
                              @PathVariable Long idTable,
                              RedirectAttributes ra) {
        try {
            // Buscamos el cliente (necesario para el mensaje de éxito)
            Customer customer = customerService.findCustomerById(idCustomer)
                    .orElseThrow(() -> new RuntimeException("Cliente ID " + idCustomer + " no encontrado."));

            // Llamamos a la lógica del servicio
            tableService.assignTableToCustomer(idTable, customer);

            ra.addFlashAttribute("success", "Mesa " + idTable + " asignada a " + customer.getName() + " " + customer.getLastname() + " con éxito.");
        } catch (RuntimeException e) {
            ra.addFlashAttribute("error", e.getMessage());
        }

        // Redirigimos al listado de mesas para ver el cambio de estado
        return "redirect:/customers/tables";
    }
}
