package com.wealth.telegram.service.consumer;

import com.wealth.telegram.constant.ValueConstant;
import com.wealth.telegram.helper.TelegramHelper;
import com.wealth.telegram.model.dto.telegram.*;
import com.wealth.telegram.model.entity.Action;
import com.wealth.telegram.model.entity.UserAction;
import com.wealth.telegram.model.enums.Status;
import com.wealth.telegram.service.TelegramService;
import com.wealth.telegram.utils.UpdateIdFileUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class RegistrationConsumerService {

    private final TelegramService telegramService;
    
    @Scheduled(fixedRate = 1200 )
    private void consumeRegistration() {
        GetUpdateResponse response = telegramService.getUpdate();
        this.process(response.getResult());
    }

    private void process(List<ResultDTO> results) {
        if (results.isEmpty()) {
            return;
        }
        UpdateIdDTO updateId = UpdateIdFileUtils.getUpdateId();
        TelegramHelper.sanitizeData(updateId, results);
        results.forEach(obj -> {
           this.subProcess(updateId, obj);
        });
    }

    private void subProcess(UpdateIdDTO updateId, ResultDTO result) {
        UpdateIdFileUtils.saveUpdateId(updateId.getCurUpdateId(), result.getUpdateId());
        MessageDTO message = result.getMessage();
        Optional<UserAction> userAction = telegramService.optUserActionIPSByUserId(message.getFrom().getId());
        this.handleUserProcess(message, userAction);
    }

    private void handleUserProcess(MessageDTO message, Optional<UserAction> opUserAction) {
        if (opUserAction.isPresent()) {
            this.handleExistingUser(message, opUserAction);
        } else {
            this.handleNewUser(message);
        }
    }

    private void handleExistingUser(MessageDTO message, Optional<UserAction> opUserAction) {
        ChatDTO chat = message.getChat();
        String command = message.getText();
        UserAction userAction = opUserAction.get();
        switch (command) {
            case ValueConstant.NO:
                this.handleNoCommand(chat, userAction);
                break;
            case ValueConstant.YES:
                this.handleYesCommand(chat, userAction);
                break;
            default:
                this.handleDefaultCommand(chat, userAction);
                break;
        }
    }

    private void handleNewUser(MessageDTO message) {
        ChatDTO chat = message.getChat();
        String command = message.getText();
        if (ValueConstant.START.equals(command)) {
            Action action = telegramService.getActionActiveByCommand(command);
            UserAction userAction = this.buildUserAction(message, action);
            telegramService.replyChat(chat, action);
            telegramService.saveUserAction(userAction);
        } else {
            telegramService.replyCommandNotFound(chat);
        }
    }

    private void handleNoCommand(ChatDTO chat, UserAction userAction) {
        Action action = telegramService.getActionActiveByCommand(ValueConstant.NO);
        this.updateUserAction(userAction, action, Status.REJECTED, true);
        telegramService.replyChat(chat, action);
        telegramService.saveUserAction(userAction);
    }

    private void handleYesCommand(ChatDTO chat, UserAction userAction) {
        Action action = Action.builder()
                .message("Oops! Sorry \uD83D\uDE4F, Our developers were not getting up \uD83D\uDE34\uD83D\uDECF\uD83D\uDCA4. " +
                        "\nPlease wait a moment \uD83D\uDD50. " +
                        "\nThey will be starting implement \uD83C\uDFD7\uD83D\uDEA7\uD83D\uDC77\u200D♂\uFE0F this feature for you soon. " +
                        "\nThank you! \uD83D\uDE4F")
                .build();
        telegramService.replyChat(chat, action);
    }

    private void handleDefaultCommand(ChatDTO chat, UserAction userAction) {
        this.updateUserAction(userAction, userAction.getAction(), userAction.getStatus(), userAction.getIsCompleted());
        telegramService.replyChat(chat, userAction.getAction());
        telegramService.saveUserAction(userAction);
    }

    //TODO correct date from to
    private UserAction buildUserAction(MessageDTO message, Action action) {
        FromDTO from = message.getFrom();
        ChatDTO chat = message.getChat();
        return UserAction.builder()
                .action(action)
                .userId(from.getId())
                .chatId(chat.getId())
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .isBot(from.isBot())
                .fromDateAt(new Date())
                .toDateAt(new Date())
                .isCompleted(false)
                .status(Status.IPS)
                .createdAt(new Date())
                .createdBy(ValueConstant.SYSTEM)
                .build();
    }

    private void updateUserAction(UserAction userAction, Action action, Status status, boolean isCompleted) {
        userAction.setAction(action);
        userAction.setStatus(status);
        userAction.setIsCompleted(isCompleted);
        userAction.setModifiedAt(new Date());
        userAction.setModifiedBy(ValueConstant.SYSTEM);
    }

}
