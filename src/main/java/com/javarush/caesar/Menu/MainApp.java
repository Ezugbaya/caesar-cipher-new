package com.javarush.caesar.Menu;

import com.javarush.caesar.analyzer.StatisticalAnalyzer;
import com.javarush.caesar.core.CaesarCipher;
import com.javarush.caesar.service.FileManager;
import com.javarush.caesar.sorting.BruteForce;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Главное меню
 */

public class MainApp {

    private final CaesarCipher cipher = new CaesarCipher();
    private final FileManager fileManager = new FileManager();
    private final BruteForce bruteForce = new BruteForce();
    private final StatisticalAnalyzer analyzer = new StatisticalAnalyzer();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new MainApp().run();
    }

    private void run() {
        while (true) {
            System.out.println("\n===ШИФР ЦЕЗАРЯ===");
            System.out.println("1. Зашифровать файл");
            System.out.println("2. Расшифровать файл (с ключом)");
            System.out.println("3. Перебор всех ключей");
            System.out.println("4. Статический взлом");
            System.out.println("0. Выход");
            System.out.print("Выберите режим: ");

            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> encrypt();
                    case "2" -> decrypt();
                    case "3" -> runBruteForce();
                    case "4" -> runStatisticalAnalysis();
                    case "0" -> {
                        System.out.println("До свидания!");
                        return;
                    }
                    default -> System.out.println("Неверный выбор.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private void encrypt() throws IOException {
        System.out.print("Исходный файл: ");
        String input = scanner.nextLine().trim();
        System.out.print("Файл для шифротекста: ");
        String output = scanner.nextLine().trim();
        System.out.print("Ключ: ");
        int key = Integer.parseInt(scanner.nextLine().trim());

        String text = fileManager.readFile(input);
        String encrypted = cipher.encrypt(text, key);
        fileManager.writeFile(encrypted, output);
        System.out.println("Шифрование завершилось");
    }

    private void decrypt() throws IOException {
        System.out.print("Зашифрованный файл: ");
        String input = scanner.nextLine().trim();
        System.out.print("Файл для расшифровки: ");
        String output = scanner.nextLine().trim();
        System.out.print("Ключ: ");
        int key = Integer.parseInt(scanner.nextLine().trim());

        String encrypted = fileManager.readFile(input);
        String decrypted = cipher.decrypt(encrypted, key);
        fileManager.writeFile(decrypted, output);
        System.out.println("Расшифровка завершена.");
    }

    private void runBruteForce() throws IOException {
        System.out.print("Зашифрованный файл: ");
        String input = scanner.nextLine().trim();
        System.out.print("Файл для результатов: ");
        String output = scanner.nextLine().trim();

        String encrypted = fileManager.readFile(input);
        List<String> results = bruteForce.decryptAll(encrypted);
        String all = String.join("\n", results);
        fileManager.writeFile(all, output);
        System.out.println("Перебор всех ключей завершен.");
    }

    private void runStatisticalAnalysis() throws IOException {
        System.out.print("Зашифрованный файл: ");
        String input = scanner.nextLine().trim();
        System.out.print("Файл для расшифровки: ");
        String output = scanner.nextLine().trim();

        String encrypted = fileManager.readFile(input);
        int key = analyzer.findBestKey(encrypted);
        String decrypted = cipher.decrypt(encrypted,key);
        fileManager.writeFile(decrypted,output);
        System.out.println("Статический взлом выполнен.");
        System.out.println("Предполагаемый ключ: "+key);
    }
}
