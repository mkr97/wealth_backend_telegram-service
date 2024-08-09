package com.wealth.telegram.model.dto.telegram;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
public class ChatDTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String type;

}
