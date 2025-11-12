package com.tecsup.Eval_S12.controller;

import com.tecsup.Eval_S12.entity.Customer;
import com.tecsup.Eval_S12.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest; // 👈 NECESARIO
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String listCustomers(Model model, HttpServletRequest request) { // 👈 Agregamos HttpServletRequest
        model.addAttribute("customers", customerService.listCustomers());
        model.addAttribute("currentPath", request.getRequestURI()); // 👈 Pasamos la URL actual
        return "customer/list_customer";
    }

    @GetMapping("/form")
    public String showCustomerForm(@RequestParam(required = false) Long id, Model model, HttpServletRequest request) { // 👈 Agregamos HttpServletRequest
        Customer customer;
        if (id != null) {
            customer = customerService.findCustomerById(id)
                    .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
        } else {
            customer = new Customer();
        }
        model.addAttribute("customer", customer);
        model.addAttribute("currentPath", request.getRequestURI()); // 👈 Pasamos la URL actual
        return "customer/form_customer";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute Customer customer, RedirectAttributes ra) {
        customerService.saveCustomer(customer);
        ra.addFlashAttribute("success", "Customer saved successfully.");
        return "redirect:/customers/list";
    }

    @PostMapping("/state/{id}/{newState}")
    public String changeCustomerState(@PathVariable Long id,
                                      @PathVariable Boolean newState,
                                      RedirectAttributes ra) {
        customerService.changeCustomerState(id, newState);
        String message = newState ? "activated" : "deactivated";
        ra.addFlashAttribute("success", "Customer ID " + id + " has been " + message + ".");
        return "redirect:/customers/list";
    }
}