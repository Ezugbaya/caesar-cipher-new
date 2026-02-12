package com.javarush.caesar.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Работа с файлами
 */

public class FileManager {

    public String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!new File(filePath).exists()) {  // проверка существования файла
            throw new FileNotFoundException("Файл не найдет: " + filePath);
        }

        //читаем построчно
        try (var lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines.collect(Collectors.joining(System.lineSeparator()));
        }
    }

    // Записываем текст в файл и создаем директорию
    public void writeFile(String content, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Path parentDir = path.getParent();
        if (parentDir != null) {
            Files.createDirectories(parentDir);
        }
        Files.write(path, content.getBytes(StandardCharsets.UTF_8));
    }
}
