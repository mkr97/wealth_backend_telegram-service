package com.wealth.telegram.utils;

import com.wealth.telegram.exception.code.AppErrorCode;
import com.wealth.telegram.exception.handler.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base32;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class HexUtils {

    public static String stringToShortHex(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            Base32 base32 = new Base32();
            String base32Hash = base32.encodeToString(hash);
            return base32Hash.substring(0, 8);
        } catch (NoSuchAlgorithmException ex) {
            log.error("An exception has occurred when stringToShortHex:", ex);
            throw new ApplicationException(AppErrorCode.INTERNAL_SYSTEM_ERROR);
        }
    }

}