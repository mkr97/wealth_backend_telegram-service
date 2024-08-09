package com.wealth.telegram.model.dto.telegram;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
public class MessageDTO implements Serializable {

    private int messageId;
    private FromDTO from;
    private ChatDTO chat;
    private long date;
    private String text;

}