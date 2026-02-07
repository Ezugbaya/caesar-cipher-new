package com.javarush.caesar.sorting;

import com.javarush.caesar.core.CaesarCipher;

import java.util.ArrayList;
import java.util.List;

/**
 * Перебор всех ключей
 */

public class BruteForce {

    private final CaesarCipher cipher = new CaesarCipher();

    public List<String> decryptAll(String encryptedText) {
        List<String> results = new ArrayList<>();
        for (int key = 0; key < CaesarCipher.AlphabetSize; key++) {
            String decrypted = cipher.decrypt(encryptedText, key);
            results.add(String.format("Ключ %2d: %s", key, decrypted));
        }
        return results;
    }

}
