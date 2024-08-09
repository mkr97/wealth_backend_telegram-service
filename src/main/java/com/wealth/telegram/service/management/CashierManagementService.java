package com.wealth.telegram.service.management;

import com.wealth.telegram.repository.CashierRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CashierManagementService {

    private final CashierRepository cashierRepo;

}
