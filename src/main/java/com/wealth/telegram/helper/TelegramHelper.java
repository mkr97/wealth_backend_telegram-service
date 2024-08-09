package com.wealth.telegram.helper;

import com.wealth.telegram.model.dto.telegram.ResultDTO;
import com.wealth.telegram.model.dto.telegram.UpdateIdDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TelegramHelper {

    public static void sanitizeData(UpdateIdDTO updateIdDTO, List<ResultDTO> results) {
        results.removeIf(obj -> isLessThanCurrent(obj.getUpdateId(), updateIdDTO.getCurUpdateId()));
    }

    private static boolean isLessThanCurrent(long updateId, long curUpdateId) {
        return updateId <= curUpdateId;
    }

}
