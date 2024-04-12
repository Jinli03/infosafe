/**
 * 功能：
 * 作者：JInli
 * 日期： 2024/4/7 16:24
 */
package one;


import java.util.Scanner;

public class Encrypt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要加密的明文");
        String plainTest = sc.next();
        System.out.println("请输入密钥");
        String key = sc.next();
        encrypt(plainTest, key);
    }
    public static void encrypt(String plainText, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyIndex = 0;

        for (char c : plainText.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift = key.charAt(keyIndex) - 'a';
                char encryptedChar = (char) ((c - 'a' + shift) % 26 + 'a');
                encryptedText.append(encryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                encryptedText.append(c);
            }
        }

        System.out.println(encryptedText.toString());
    }
}
