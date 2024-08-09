package com.wealth.telegram.repository;

import com.wealth.telegram.model.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {

}
