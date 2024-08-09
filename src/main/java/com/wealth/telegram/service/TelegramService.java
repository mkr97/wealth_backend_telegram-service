package com.wealth.telegram.service;

import com.wealth.telegram.model.dto.telegram.ChatDTO;
import com.wealth.telegram.model.dto.telegram.GetUpdateResponse;
import com.wealth.telegram.model.entity.Action;
import com.wealth.telegram.model.entity.UserAction;

import java.util.Optional;

public interface TelegramService {

    GetUpdateResponse getUpdate();

    void replyChat(ChatDTO chat, Action action);

    void replyCommandNotFound(ChatDTO chat);

    Optional<UserAction> optUserActionIPSByUserId(long userId);

    Action getActionActiveByCommand(String command);

    void saveUserAction(UserAction userAction);

}
