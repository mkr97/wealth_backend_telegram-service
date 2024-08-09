package com.wealth.telegram.repository;

import com.wealth.telegram.model.entity.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashierRepository extends JpaRepository<Cashier, Long> {
}
