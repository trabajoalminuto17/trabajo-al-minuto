/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.model.business;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author BASP
 */
public class BusinessSecurity {

    /**
     * Se encarga de encriptar la contraseña ingresada por el usuario *
     */
    public static String encrypt(String value) throws BusinessException {
        String secretKey = "e-business";
        String base64EncryptedString = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainTextBytes = value.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
        return base64EncryptedString;
    }

    /**
     * Se encarga de desencriptar la contraseña ingresada por el usuario *
     */
    public static String decrypt(String encryptValue) throws BusinessException {
        String secretKey = "e-business";
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(encryptValue.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainText = decipher.doFinal(message);
            base64EncryptedString = new String(plainText, "UTF-8");
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
        return base64EncryptedString;
    }

}
