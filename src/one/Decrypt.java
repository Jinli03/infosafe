/**
 * 功能：
 * 作者：JInli
 * 日期： 2024/4/7 16:35
 */
package one;

import java.util.Scanner;

public class Decrypt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要解密的密文");
        String encryptedText = sc.next();
        System.out.println("请输入密钥");
        String key = sc.next();
        decrypt(encryptedText, key);
    }
    public static void decrypt(String encryptedText, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyIndex = 0;

        for (char c : encryptedText.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift = key.charAt(keyIndex) - 'a';
                char decryptedChar = (char) ((c - 'a' - shift + 26) % 26 + 'a');
                decryptedText.append(decryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                decryptedText.append(c);
            }
        }
        System.out.println(decryptedText.toString());
    }
}
