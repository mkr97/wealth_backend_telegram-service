package com.wealth.telegram.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class AESUtils {

    private static Key generateKey(String value) {
        byte[] keyAsBytes;
        keyAsBytes = value.getBytes();
        return new SecretKeySpec(keyAsBytes, "AES");
    }

    public static String aesEncrypt(String valueToEncrypt, String aesKey) {
        try {
            Key key = generateKey(aesKey);
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encValue = c.doFinal(valueToEncrypt.getBytes());
            return Base64.encodeBase64String(encValue);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String aesDecrypt(String encryptedValue, String aesKey){
        try {
            Key key = generateKey(aesKey);
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedValue = new Base64().decode(encryptedValue.getBytes());
            byte[] decValue = c.doFinal(decodedValue);
            return new String(decValue);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}