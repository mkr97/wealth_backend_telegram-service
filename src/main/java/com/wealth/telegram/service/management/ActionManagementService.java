package com.wealth.telegram.service.management;

import com.wealth.telegram.exception.code.TelegramErrorCode;
import com.wealth.telegram.exception.handler.ApplicationException;
import com.wealth.telegram.model.entity.Action;
import com.wealth.telegram.model.enums.Status;
import com.wealth.telegram.repository.ActionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ActionManagementService {

    private final ActionRepository actionRepo;

    public Optional<Action> optActiveByCommand(String command) {
        return actionRepo.findFirstByCommandAndStatus(command, Status.ACTIVE);
    }

    public Action getActiveByCommand(String command) {
        return actionRepo.findFirstByCommandAndStatus(command, Status.ACTIVE)
                .orElseThrow(() -> new ApplicationException(TelegramErrorCode.ACTION_NOT_FOUND));
    }


}
