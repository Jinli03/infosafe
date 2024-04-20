package two;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class Aes2 {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // 输入密钥
        System.out.println("请输入16个字节的密钥（十六进制格式）：");
        String key = scanner.nextLine();

        // 输入明文
        System.out.println("请输入明文（十六进制格式）：");
        String plainText = scanner.nextLine();

        // 加密
        String encryptedText = encrypt(plainText, key);
        System.out.println("加密结果：");
        System.out.println(encryptedText);

        // 解密
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("解密结果：");
        System.out.println(decryptedText);
    }

    // 加密方法
    public static String encrypt(String plainText, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(hexStringToByteArray(key), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(hexStringToByteArray(plainText));
        return byteArrayToHexString(encryptedBytes);
    }

    // 解密方法
    public static String decrypt(String encryptedText, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(hexStringToByteArray(key), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(hexStringToByteArray(encryptedText));
        return byteArrayToHexString(decryptedBytes);
    }

    // 将十六进制字符串转换为字节数组
    private static byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();
        byte[] byteArray = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            byteArray[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return byteArray;
    }

    // 将字节数组转换为十六进制字符串
    private static String byteArrayToHexString(byte[] byteArray) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : byteArray) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }
}
