package com.javarush.caesar.core;

/**
 * Ядро шифра
 */


public class CaesarCipher {

    private static final String Alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static final int AlphabetSize = Alphabet.length();

    // Шифруем текст с заданным ключом
    public String encrypt(String text, int key) {
        return transform(text, key);
    }

    // Расшифровываем текст с заданным ключом
    public String decrypt(String text, int key) {
        return transform(text, -key);
    }

    // Сдвигаем символы по алфавиту
    private String transform(String text, int shift) {
        shift = shift % AlphabetSize;
        if (shift < 0) {
            shift += AlphabetSize;
        }
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                char lowerChar = Character.toLowerCase(c);

                // Ищем буквы в алфавите - только русские
                int index = Alphabet.indexOf(lowerChar);
                if (index != -1) { //буква найдена - шифруем
                    int newIndex = (index + shift) % AlphabetSize;
                    char newCar = Alphabet.charAt(newIndex);
                    result.append(isUpper ? Character.toUpperCase(newCar) : newCar);
                } else { // не русская буква - ничего не делаем
                    result.append(c);
                }
            } else {  // не буква
                result.append(c);
            }
        }
        return result.toString();
    }
}