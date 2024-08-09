package com.wealth.telegram.model.dto.telegram;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateIdDTO {

    private long curUpdateId;
    private long preUpdateId;

}