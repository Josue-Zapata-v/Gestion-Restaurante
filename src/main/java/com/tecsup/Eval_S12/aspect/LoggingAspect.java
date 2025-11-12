package com.tecsup.Eval_S12.aspect;

import com.tecsup.Eval_S12.entity.Bitacora;
import com.tecsup.Eval_S12.entity.Customer;
import com.tecsup.Eval_S12.repository.BitacoraRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LoggingAspect {

    private final BitacoraRepository bitacoraRepository;

    public LoggingAspect(BitacoraRepository bitacoraRepository) {
        this.bitacoraRepository = bitacoraRepository;
    }

    private String getAuthenticatedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "SISTEMA";
    }

    // --- AUDITORÍA DE CLIENTES ---

    /**
     * Intercepta el método saveCustomer en CustomerService.
     * pointcut: execution(* com.tecsup.Eval_S12.service.CustomerService.saveCustomer(..))
     */
    @AfterReturning(
            pointcut = "execution(* com.tecsup.Eval_S12.service.CustomerService.saveCustomer(..))", // CORREGIDO
            returning = "result"
    )
    public void logCustomerSave(JoinPoint joinPoint, Customer result) {
        String username = getAuthenticatedUserName();
        String tipoAccion;
        String detalle;

        if (result.getIdCustomer() == null) {
            tipoAccion = "CREACIÓN";
            detalle = "Customer Creado. ID Asignado: " + result.getIdCustomer() + ", DNI: " + result.getDni();
        } else {
            tipoAccion = "ACTUALIZACIÓN";
            detalle = "Customer Actualizado. ID: " + result.getIdCustomer() + ", DNI: " + result.getDni();
        }

        Bitacora bitacora = new Bitacora(
                username,
                tipoAccion + " - " + detalle,
                LocalDateTime.now()
        );
        bitacoraRepository.save(bitacora);
    }

    /**
     * Intercepta el método changeCustomerState en CustomerService.
     * pointcut: execution(* com.tecsup.Eval_S12.service.CustomerService.changeCustomerState(..))
     */
    @Before("execution(* com.tecsup.Eval_S12.service.CustomerService.changeCustomerState(..)) && args(customerId, newState)") // CORREGIDO
    public void logCustomerStateChange(Long customerId, Boolean newState) {
        String username = getAuthenticatedUserName();
        String estadoTexto = newState ? "ACTIVADO" : "DESACTIVADO";

        String detalle = String.format("ID: %d - Nuevo Estado Lógico: %s", customerId, estadoTexto);

        Bitacora bitacora = new Bitacora(
                username,
                "ESTADO CLIENTE - " + detalle,
                LocalDateTime.now()
        );
        bitacoraRepository.save(bitacora);
    }

    // --- AUDITORÍA DE MESAS ---

    /**
     * Intercepta el cambio de estado de Mesa.
     * pointcut: execution(* com.tecsup.Eval_S12.service.TableService.changeTableState(..))
     */
    @Before("execution(* com.tecsup.Eval_S12.service.TableService.changeTableState(..)) && args(mesaId, nuevoEstado)") // CORREGIDO
    public void logMesaStateChange(JoinPoint joinPoint, Long mesaId, Enum<?> nuevoEstado) {
        String username = getAuthenticatedUserName();
        String detalle = String.format("Mesa %d - Nuevo Estado: %s", mesaId, nuevoEstado.name());

        Bitacora bitacora = new Bitacora(
                username,
                "ESTADO MESA - " + detalle,
                LocalDateTime.now()
        );
        bitacoraRepository.save(bitacora);
    }
}