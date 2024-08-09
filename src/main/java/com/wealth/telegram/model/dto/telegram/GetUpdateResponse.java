package com.wealth.telegram.model.dto.telegram;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@SuperBuilder
@AllArgsConstructor
public class GetUpdateResponse implements Serializable {

    private boolean ok;

    private List<ResultDTO> result;

    public List<ResultDTO> getResult() {
        return Optional.ofNullable(this.result)
                .orElseGet(ArrayList::new);
    }

}
