package com.javarush.caesar.analyzer;

import java.util.HashMap;
import java.util.Map;

/**
 * Статический анализ при расшифровке
 */
public class StatisticalAnalyzer {

    private static final String Alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public int findBestKey(String encryptedText) {
        Map<Character, Integer> frequency = new HashMap<>();

        // Пройдемся по всем символам
        for (char c : encryptedText.toLowerCase().toCharArray()) {
            if (Alphabet.indexOf(c) != -1) { // Если русская буква увеличиваем счётчик
                frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            }
        }
        if (frequency.isEmpty()){ // Если нет русских букв
            return 0;
        }

        // Самая частая буква
        char mostFrequentChar = 'a';
        int maxCount = 0;

        for (Map.Entry<Character,Integer> entry : frequency.entrySet()){
            char letter = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount){
                maxCount = count;
                mostFrequentChar = letter;
            }
        }

        // Вычисляем ключ
        int indexEncrypted = Alphabet.indexOf(mostFrequentChar);
        int indexOriginal = Alphabet.indexOf('о'); // 'о' самая частая буква
        int alphabetSize = Alphabet.length();

        int key = (indexEncrypted - indexOriginal + alphabetSize) % alphabetSize;

        return key;
        }
    }


