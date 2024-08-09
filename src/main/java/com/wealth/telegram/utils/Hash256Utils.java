package com.wealth.telegram.utils;

import com.wealth.telegram.exception.code.AppErrorCode;
import com.wealth.telegram.exception.handler.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.security.MessageDigest;

@Slf4j
public class Hash256Utils {

    public static String hash256(String value) {
        try {
            Assert.notNull(value, "To hash256 data must be not null.");
            return Hash256Utils.hash256(value);
        } catch (Exception ex) {
            log.error("An exception has occurred when hash256 :", ex);
            throw new ApplicationException(AppErrorCode.INTERNAL_SYSTEM_ERROR);
        }
    }

    public static String generateSHA256Hash(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(value.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            log.error("An exception has occurred when generateSHA256Hash:", ex);
            throw new ApplicationException(AppErrorCode.INTERNAL_SYSTEM_ERROR);
        }
    }

}