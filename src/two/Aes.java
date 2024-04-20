/**
 * 功能：
 * 作者：JInli
 * 日期： 2024/4/13 22:05
 */
package two;

import java.util.Scanner;

public class Aes {

    private static final int[][] S = {
            {0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76},
            {0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0},
            {0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15},
            {0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75},
            {0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84},
            {0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf},
            {0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8},
            {0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2},
            {0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73},
            {0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb},
            {0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79},
            {0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08},
            {0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a},
            {0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e},
            {0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf},
            {0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16}
    };

    private static final int[][] inverseS = {
            {0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb},
            {0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb},
            {0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e},
            {0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25},
            {0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92},
            {0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84},
            {0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06},
            {0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b},
            {0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73},
            {0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e},
            {0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b},
            {0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4},
            {0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f},
            {0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef},
            {0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61},
            {0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d}
    };


    private static final int[][] Rcon = {
            {0x00, 0x00, 0x00, 0x00},
            {0x01, 0x00, 0x00, 0x00},
            {0x02, 0x00, 0x00, 0x00},
            {0x04, 0x00, 0x00, 0x00},
            {0x08, 0x00, 0x00, 0x00},
            {0x10, 0x00, 0x00, 0x00},
            {0x20, 0x00, 0x00, 0x00},
            {0x40, 0x00, 0x00, 0x00},
            {0x80, 0x00, 0x00, 0x00},
            {0x1b, 0x00, 0x00, 0x00},
            {0x36, 0x00, 0x00, 0x00}
    };

    private static final int Nb = 4;

    private static int[][] addRoundKey(int[][] state, int[][] w, int round) {
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                state[r][c] ^= w[4 * round + r][c];
            }
        }
        return state;
    }


    private static int[][] subBytes(int[][] state) {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                state[r][c] = S[state[r][c] / 16][state[r][c] % 16];
            }
        }
        return state;
    }

    private static int[][] shiftRows(int[][] state) {
        int[][] tmp = new int[4][4];
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                tmp[r][c] = state[r][(c + r) % Nb];
            }
        }
        return tmp;
    }

    private static int[][] mixColumns(int[][] state) {
        int[][] tmp = new int[4][4];
        for (int c = 0; c < 4; c++) {
            //
            //02 03 01 01
            tmp[0][c] = (mul(0x02, state[0][c]) ^ mul(0x03, state[1][c]) ^ state[2][c] ^ state[3][c]);
            //01 02 03 01
            tmp[1][c] = (state[0][c] ^ mul(0x02, state[1][c]) ^ mul(0x03, state[2][c]) ^ state[3][c]);
            //01 01 02 03
            tmp[2][c] = (state[0][c] ^ state[1][c] ^ mul(0x02, state[2][c]) ^ mul(0x03, state[3][c]));
            //03 01 01 02
            tmp[3][c] = (mul(0x03, state[0][c]) ^ state[1][c] ^ state[2][c] ^ mul(0x02, state[3][c]));
        }
        return tmp;
    }

    private static int[][] keyExpansion(int[] key) {
        int[][] w = new int[44][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                w[i][j] = key[i * 4 + j];
            }
        }
        for (int i = 4; i < 44; i++) {
            int[] temp = new int[4];
            for (int j = 0; j < 4; j++) {
                temp[j] = w[i - 1][j];
            }
            if (i % 4 == 0) {
                temp = subWord(rotWord(temp));
                for (int j = 0; j < 4; j++) {
                    temp[j] ^= Rcon[i / 4][j];
                }
            }
            for (int j = 0; j < 4; j++) {
                w[i][j] = w[i - 4][j] ^ temp[j];
            }
        }
        return w;
    }

    //字节代换
    private static int[] subWord(int[] word) {
        int[] temp = new int[4];
        for (int i = 0; i < 4; i++) {
            temp[i] = S[word[i] / 16][word[i] % 16];
        }
        return temp;
    }

    //字循环，向上移一位
    private static int[] rotWord(int[] word) {
        int temp = word[0];
        for (int i = 0; i < 3; i++) {
            word[i] = word[i + 1];
        }
        word[3] = temp;
        return word;
    }

    private static int mul(int a, int b) {
        int p = 0;
        for (int i = 0; i < 8; i++) {
            if ((b & 1) != 0) {
                p ^= a;
            }
            boolean hi_bit_set = (a & 0x80) != 0;
            a <<= 1;
            if (hi_bit_set) {
                a ^= 0x1b;
            }
            b >>= 1;
        }
        return p & 0xFF;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%02X ", value);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] encrypt(int[][] plaintext, int[] key) {
        int[][] state = new int[4][4];
        int[][] w = keyExpansion(key);

        // 初始化状态矩阵
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                state[r][c] = plaintext[r][c];
            }
        }

        // 轮密钥加（初始变换）
        state = addRoundKey(state, w, 0);

        // 9 轮加密
        for (int round = 1; round <= 9; round++) {
            state = subBytes(state);
            state = shiftRows(state);
            state = mixColumns(state);
            state = addRoundKey(state, w, round);
        }

        // 最后一轮加密
        state = subBytes(state);
        state = shiftRows(state);
        state = addRoundKey(state, w, 10);

        return state;
    }

    private static int[][] inverseShiftRows(int[][] state) {
        int[][] tmp = new int[4][4];
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                tmp[r][(c + 4 - r) % Nb] = state[r][c];
            }
        }
        return tmp;
    }

    private static int[][] inverseSubBytes(int[][] state) {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                state[r][c] = inverseS[state[r][c] / 16][state[r][c] % 16];
            }
        }
        return state;
    }

    private static int[][] inverseMixColumns(int[][] state) {
        int[][] tmp = new int[4][4];
        for (int c = 0; c < 4; c++) {
            tmp[0][c] = (mul(0x0e, state[0][c]) ^ mul(0x0b, state[1][c]) ^ mul(0x0d, state[2][c]) ^ mul(0x09, state[3][c]));
            tmp[1][c] = (mul(0x09, state[0][c]) ^ mul(0x0e, state[1][c]) ^ mul(0x0b, state[2][c]) ^ mul(0x0d, state[3][c]));
            tmp[2][c] = (mul(0x0d, state[0][c]) ^ mul(0x09, state[1][c]) ^ mul(0x0e, state[2][c]) ^ mul(0x0b, state[3][c]));
            tmp[3][c] = (mul(0x0b, state[0][c]) ^ mul(0x0d, state[1][c]) ^ mul(0x09, state[2][c]) ^ mul(0x0e, state[3][c]));
        }
        return tmp;
    }


    public static int[][] decrypt(int[][] ciphertext, int[] key) {
        int[][] state = new int[4][4];
        int[][] w = keyExpansion(key);

        // 初始化状态矩阵
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                state[r][c] = ciphertext[r][c];
            }
        }

        // 初始轮密钥加
        state = addRoundKey(state, w, 10);

        // 9 轮解密
        for (int round = 9; round >= 1; round--) {
            state = inverseShiftRows(state);
            state = inverseSubBytes(state);
            state = addRoundKey(state, w, round);
            state = inverseMixColumns(state);
        }

        // 最后一轮解密
        state = inverseShiftRows(state);
        state = inverseSubBytes(state);
        state = addRoundKey(state, w, 0);

        return state;
    }

    private static String stateToString(int[][] state) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                sb.append(String.format("%02X", state[r][c]));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入16个字节的密钥（十六进制格式）：");
        int[] key = new int[16];
        String keyString = scanner.next();
        if (keyString.length() != 32) {
            System.out.println("无效的密钥长度，请输入32个十六进制字符。");
            return;
        }
        for (int i = 0; i < 16; i++) {
            key[i] = Integer.parseInt(keyString.substring(i * 2, i * 2 + 2), 16);
        }

        System.out.println("请输入16个字节的明文（十六进制格式）：");
        String stateString = scanner.next();
        if (stateString.length() != 32) {
            System.out.println("无效的明文长度，请输入32个十六进制字符。");
            return;
        }
        int[][] state = new int[4][4];
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                state[r][c] = Integer.parseInt(stateString.substring((c * 4 + r) * 2, (c * 4 + r) * 2 + 2), 16);
            }
        }

        int[][] encrypted = encrypt(state, key);
        System.out.println("加密结果：");
        System.out.println(stateToString(encrypted));

        int[][] decrypted = decrypt(encrypted, key);
        System.out.println("解密结果：");
        System.out.println(stateToString(decrypted));
    }
}