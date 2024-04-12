package one;

import java.util.HashMap;
import java.util.Map;

public class Dif {

    // 假设英语字母频率表
    private static final double[] ENGLISH_LETTER_FREQUENCY = {
            0.082, 0.015, 0.028, 0.043, 0.127, 0.022, 0.020, 0.061, 0.070, 0.002, 0.008,
            0.040, 0.024, 0.067, 0.075, 0.019, 0.001, 0.060, 0.063, 0.091, 0.028, 0.010,
            0.023, 0.001, 0.020, 0.001
    };

    // 计算频率
    private static double[] calculateFrequency(String text) {
        int[] count = new int[26];
        int total = 0;
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                count[Character.toLowerCase(c) - 'a']++;
                total++;
            }
        }
        double[] frequency = new double[26];
        for (int i = 0; i < 26; i++) {
            frequency[i] = (double) count[i] / total;
        }
        return frequency;
    }

    // 计算相似度
    private static double calculateSimilarity(double[] freq1, double[] freq2) {
        double similarity = 0;
        for (int i = 0; i < 26; i++) {
            similarity += Math.abs(freq1[i] - freq2[i]);
        }
        return similarity;
    }

    // 破解密文
    public static String crack(String ciphertext, int keyLength) {
        StringBuilder crackedText = new StringBuilder();


        for (int i = 1; i <= keyLength; i++) {
            StringBuilder keyBuilder = new StringBuilder();
            for (int j = 0; j < i; j++) {
                StringBuilder currentGroup = new StringBuilder();
                for (int k = j; k < ciphertext.length(); k += i) {
                    currentGroup.append(ciphertext.charAt(k));
                }
                double minSimilarity = Double.MAX_VALUE;
                char guessedChar = 'a';
                for (char c = 'a'; c <= 'z'; c++) {
                    double[] groupFrequency = calculateFrequency(currentGroup.toString());
                    double[] shiftedFrequency = new double[26];
                    for (int l = 0; l < 26; l++) {
                        shiftedFrequency[l] = groupFrequency[(l + (c - 'a')) % 26];
                    }
                    double similarity = calculateSimilarity(shiftedFrequency, ENGLISH_LETTER_FREQUENCY);
                    if (similarity < minSimilarity) {
                        minSimilarity = similarity;
                        guessedChar = c;
                    }
                }
                keyBuilder.append(guessedChar);
            }
            String key = keyBuilder.toString();


            StringBuilder decryptedText = new StringBuilder();
            for (int j = 0; j < ciphertext.length(); j++) {
                char encryptedChar = ciphertext.charAt(j);
                char keyChar = key.charAt(j % key.length());
                int shift = keyChar - 'a';
                char decryptedChar;
                if (Character.isUpperCase(encryptedChar)) {
                    decryptedChar = (char) (((encryptedChar - 'A' - shift + 26) % 26) + 'A');
                } else {
                    decryptedChar = (char) (((encryptedChar - 'a' - shift + 26) % 26) + 'a');
                }
                decryptedText.append(decryptedChar);
            }
            crackedText.append("Key: ").append(key).append(", Decrypted Text: ").append(decryptedText.toString()).append("\n");
        }

        return crackedText.toString();
    }



    public static void main(String[] args) {
        String ciphertext = "tigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunrspxesvhfnaaadpitigqvkcldrpyngqxkwmquowgrujemczzfohvhfsujekctoxpfpzjvoptqvfttiglbbyeqgujerwidmbsqwohoylunr\n";
        int maxLength = 4;
        for (int i = 1; i <= maxLength; i++) {
            System.out.println("Trying key length: " + i);
            String crackedResult = crack(ciphertext, i);
            System.out.println(crackedResult);
            System.out.println("--------------------------");
        }
    }
}
