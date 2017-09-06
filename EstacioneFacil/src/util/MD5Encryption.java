package util;

import org.apache.commons.codec.binary.Base64;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Douglas
 */
public class MD5Encryption {

    private static final byte[] SHARED_VECTOR = {0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11};
    private static final String KEY = "sace";

    public String encrypt(String RawText) {
        String encText = "";
        byte[] keyArray = new byte[24];
        byte[] temporaryKey;
        byte[] toEncryptArray = null;

        try {
            toEncryptArray = RawText.getBytes("UTF-8");
            MessageDigest m = MessageDigest.getInstance("MD5");
            temporaryKey = m.digest(KEY.getBytes("UTF-8"));

            if (temporaryKey.length < 24) {
                int index = 0;
                for (int i = temporaryKey.length; i < 24; i++) {
                    keyArray[i] = temporaryKey[index];
                }
            }
            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(SHARED_VECTOR));
            byte[] encrypted = c.doFinal(toEncryptArray);
            encText = new String(Base64.encodeBase64(encrypted));

        } catch (Exception e) {
           e.printStackTrace();
        }
        return encText;
    }

    public String decrypt(String encText) {
        String rawText = "";
        byte[] keyArray = new byte[24];
        byte[] temporaryKey;

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            temporaryKey = m.digest(KEY.getBytes("UTF-8"));

            if (temporaryKey.length < 24) {
                int index = 0;
                for (int i = temporaryKey.length; i < 24; i++) {
                    keyArray[i] = temporaryKey[index];
                }
            }
            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(SHARED_VECTOR));
            byte[] decrypted = encText.getBytes();
            decrypted = c.doFinal(Base64.decodeBase64(decrypted));

            rawText = new String(decrypted, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rawText;
    }
}
