package com.wealth.telegram.repository;

import com.wealth.telegram.model.entity.UserAction;
import com.wealth.telegram.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserActionRepository extends JpaRepository<UserAction, Long> {

    Optional<UserAction> findFirstByUserIdAndStatus(long userId, Status status);

}
