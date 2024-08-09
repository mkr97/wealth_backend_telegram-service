package com.wealth.telegram.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
public class RSAUtils {

    public RSAUtils() {
    }

    public static RSAPublicKey readPublicKeyAsText(String key) throws Exception {
        String publicKeyContent = key.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "").replaceAll("[\n|\r]", "");
        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
        RSAPublicKey pubKey = (RSAPublicKey)kf.generatePublic(keySpecX509);
        return pubKey;
    }

    public static RSAPublicKey readPublicKeyFromFile(String filename) throws Exception {
        log.info("Load public key with file name :{}", filename);
        String publicKeyContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(filename).toURI())));
        publicKeyContent = publicKeyContent.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "").replaceAll("[\n|\r]", "");
        KeyFactory kf = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
        RSAPublicKey pubKey = (RSAPublicKey)kf.generatePublic(keySpecX509);
        return pubKey;
    }

    public static PrivateKey readPrivateKeyAsText(String key) throws Exception {
        String privateKeyContent = key.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "").replaceAll("[\n|\r]", "");
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        PrivateKey privKey = kf.generatePrivate(keySpecPKCS8);
        return privKey;
    }

    public static PrivateKey readPrivateKey(String filename) throws Exception {
        String privateKeyContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(filename).toURI())));
        privateKeyContent = privateKeyContent.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "").replaceAll("[\n|\r]", "");
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        PrivateKey privKey = kf.generatePrivate(keySpecPKCS8);
        return privKey;
    }

    public static String encryptByKeyAsText(String plainText, String key) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(1, readPublicKeyAsText(key));
        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String encrypt(String plainText, String fileName) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(1, readPublicKeyFromFile(fileName));
        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static byte[] encryptStringToByte(String plainText, String fileName) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(1, readPublicKeyFromFile(fileName));
        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return cipherText;
    }

    public static String decryptByKeyAsText(String cipherText, String key) {
        try {
            byte[] bytes = Base64.getDecoder().decode(cipherText);
            Cipher decriptCipher = Cipher.getInstance("RSA");
            decriptCipher.init(2, readPrivateKeyAsText(key));
            return new String(decriptCipher.doFinal(bytes), StandardCharsets.UTF_8);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String cipherText, String fileName) {
        try {
            byte[] bytes = Base64.getDecoder().decode(cipherText);
            Cipher decriptCipher = Cipher.getInstance("RSA");
            decriptCipher.init(2, readPrivateKey(fileName));
            return new String(decriptCipher.doFinal(bytes), StandardCharsets.UTF_8);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static String convertToHexString(byte[] data) {
        StringBuffer buf = new StringBuffer();

        for(int i = 0; i < data.length; ++i) {
            int halfbyte = data[i] >>> 4 & 15;
            int two_halfs = 0;

            do {
                if (0 <= halfbyte && halfbyte <= 9) {
                    buf.append((char)(48 + halfbyte));
                } else {
                    buf.append((char)(97 + (halfbyte - 10)));
                }

                halfbyte = data[i] & 15;
            } while(two_halfs++ < 1);
        }

        return buf.toString();
    }

    public static String encryptWithHexString(String planText, String filePublicKey) {
        try {
            return convertToHexString(encryptStringToByte(planText, filePublicKey));
        } catch (Exception var3) {
            log.error("Encryption error : ", var3);
            return null;
        }
    }

    public static PublicKey getPublicKeyByDer(String filename) throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(filename).toURI()));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }
}