package com.wealth.telegram.service.impl;

import com.wealth.telegram.config.feign.TelegramFeignClient;
import com.wealth.telegram.constant.ValueConstant;
import com.wealth.telegram.model.dto.telegram.ChatDTO;
import com.wealth.telegram.model.dto.telegram.GetUpdateResponse;
import com.wealth.telegram.model.dto.telegram.SendMessageRequest;
import com.wealth.telegram.model.entity.Action;
import com.wealth.telegram.model.entity.UserAction;
import com.wealth.telegram.service.TelegramService;
import com.wealth.telegram.service.management.ActionManagementService;
import com.wealth.telegram.service.management.UserActionManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramServiceImpl implements TelegramService {

    @Value("${telegram.bot.token}")
    private String botToken;

    private final TelegramFeignClient telegramFeign;
    private final UserActionManagementService userActionService;
    private final ActionManagementService actionService;

    @Override
    public GetUpdateResponse getUpdate() {
        GetUpdateResponse response = telegramFeign.getUpdate(botToken);
        if (Boolean.FALSE.equals(response.isOk())) {
            log.info("Something went wrong with telegram getUpdate");
        }
        return response;
    }

    @Override
    public void replyChat(ChatDTO chat, Action action) {
        String message = this.replaceMessage(chat, action);
        action.setMessage(message);
        SendMessageRequest body = this.buildSendMessage(chat, action);
        telegramFeign.sendMessage(botToken, body);
    }

    @Override
    public void replyCommandNotFound(ChatDTO chat) {
        Action action = this.getActionActiveByCommand(ValueConstant.COMMAND_NOT_FOUND);
        String message = this.replaceMessage(chat, action);
        action.setMessage(message);
        SendMessageRequest body = this.buildSendMessage(chat, action);
        telegramFeign.sendMessage(botToken, body);
    }

    @Override
    public Optional<UserAction> optUserActionIPSByUserId(long userId) {
        return userActionService.optISPByUserId(userId);
    }

    @Override
    public Action getActionActiveByCommand(String command) {
        return actionService.getActiveByCommand(command);
    }

    @Override
    public void saveUserAction(UserAction userAction) {
        userActionService.save(userAction);
    }

    private String replaceMessage(ChatDTO chat, Action action) {
        return action.getMessage()
                .replaceAll("#first_name#", chat.getFirstName())
                .replaceAll("#last_name#", chat.getLastName());
    }

    private SendMessageRequest buildSendMessage(ChatDTO chat, Action action) {
        return SendMessageRequest.builder()
                .chatId(chat.getId().toString())
                .text(action.getMessage())
                .parseMode(ValueConstant.MARKDOWN)
                .build();
    }

}
