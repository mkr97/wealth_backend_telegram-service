package com.wealth.telegram.service.management;

import com.wealth.telegram.model.entity.UserAction;
import com.wealth.telegram.model.enums.Status;
import com.wealth.telegram.repository.UserActionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserActionManagementService {

    private final UserActionRepository userActionRepo;

    public Optional<UserAction> optISPByUserId(long userId) {
        return userActionRepo.findFirstByUserIdAndStatus(userId, Status.IPS);
    }

    public void save(UserAction userAction) {
        userActionRepo.save(userAction);
    }

}
