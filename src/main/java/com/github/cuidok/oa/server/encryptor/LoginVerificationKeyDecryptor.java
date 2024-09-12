package com.github.cuidok.oa.server.encryptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class LoginVerificationKeyDecryptor {

    private static final String AES = "AES";

    @Value("${oa.server.encryptor.aes.key}")
    private String SECRET_KEY;

    public String decrypt(String encryptedKey) {

        byte[] encryptedKeyBytes = Base64.getDecoder().decode(encryptedKey);

        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), AES);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedKeyBytes = cipher.doFinal(encryptedKeyBytes);
            return new String(decryptedKeyBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
