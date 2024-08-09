package com.wealth.telegram.utils;

import com.wealth.telegram.exception.handler.ApplicationException;
import com.wealth.telegram.model.dto.telegram.UpdateIdDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

import static com.wealth.telegram.exception.code.AppErrorCode.FAILED_READ_FILE;
import static com.wealth.telegram.exception.code.AppErrorCode.FAILED_WRITE_FILE;

@Slf4j
public class UpdateIdFileUtils {

    private static final String FILE_PATH = "telegram_update_id.csv";

    public static UpdateIdDTO getUpdateId() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                if (line != null) {
                    line = reader.readLine();
                    if (line != null) {
                        String[] values = line.split(",");
                        return UpdateIdDTO.builder()
                                .preUpdateId(Long.parseLong(values[0]))
                                .curUpdateId(Long.parseLong(values[1]))
                                .build();
                    }
                }
            } catch (IOException e) {
                throw new ApplicationException(FAILED_READ_FILE);
            }
        }
        return UpdateIdDTO.builder()
                .build();
    }

    public static void saveUpdateId(long preUpdateId, long curUpdateId) {
        File file = new File(FILE_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("pre_update_id,cur_update_id");
            writer.newLine();
            writer.write(String.format("%d,%d",preUpdateId,curUpdateId));
        } catch (IOException e) {
            throw new ApplicationException(FAILED_WRITE_FILE);
        }
    }
}
