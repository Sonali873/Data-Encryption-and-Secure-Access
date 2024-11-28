package com.medica_helthcare.util;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptionUtil {

    private static final String ALGORITHM = "AES";

    // Generate a new AES key
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(256, new SecureRandom());
        return keyGen.generateKey();
    }

    // Encrypt a file
    public static void encryptFile(SecretKey key, File inputFile, File outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)) {
           // byte[] inputBytes = inputStream.readAllBytes();
            //byte[] outputBytes = cipher.doFinal(inputBytes);
            //outputStream.write(outputBytes);
        }
    }

    // Decrypt a file
    public static void decryptFile(SecretKey key, File inputFile, File outputFile) throws Exception {
    	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(new byte[16]); // Same IV for encryption and decryption
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)) {

//            byte[] inputBytes = inputStream.readAllBytes();
//            byte[] outputBytes = cipher.doFinal(inputBytes);

           // outputStream.write(outputBytes);
        }
    }

    // Convert SecretKey to Base64 String
    public static String keyToString(SecretKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    // Convert Base64 String to SecretKey
    public static SecretKey stringToKey(String keyStr) {
        byte[] decodedKey = Base64.getDecoder().decode(keyStr);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
    }
}
