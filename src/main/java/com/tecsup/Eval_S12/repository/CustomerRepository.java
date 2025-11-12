package com.tecsup.Eval_S12.repository;

import com.tecsup.Eval_S12.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}