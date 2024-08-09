package com.wealth.telegram.service.management;

import com.wealth.telegram.repository.MerchantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MerchantManagementService {

    private final MerchantRepository merchantRepo;

}
