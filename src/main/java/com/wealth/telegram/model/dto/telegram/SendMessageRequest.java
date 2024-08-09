package com.wealth.telegram.model.dto.telegram;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
public class SendMessageRequest implements Serializable {

    private String chatId;
    private String text;
    private String parseMode;

}
