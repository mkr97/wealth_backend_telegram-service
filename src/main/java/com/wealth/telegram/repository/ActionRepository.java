package com.wealth.telegram.repository;

import com.wealth.telegram.model.entity.Action;
import com.wealth.telegram.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActionRepository extends JpaRepository<Action, Long> {

    Optional<Action> findFirstByCommandAndStatus(String command, Status status);

}
