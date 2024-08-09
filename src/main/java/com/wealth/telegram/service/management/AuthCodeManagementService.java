package com.wealth.telegram.service.management;

import com.wealth.telegram.repository.AuthCodeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AuthCodeManagementService {

    private final AuthCodeRepository authCodeRepo;

}
