package com.wealth.telegram.repository;

import com.wealth.telegram.model.entity.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthCodeRepository extends JpaRepository<AuthCode, Long> {

}
