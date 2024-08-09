package com.wealth.telegram.model.dto.telegram;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
public class ResultDTO implements Serializable {

    private long updateId;

    private MessageDTO message;

}
