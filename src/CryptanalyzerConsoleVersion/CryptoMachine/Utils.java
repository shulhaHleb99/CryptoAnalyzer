package CryptanalyzerConsoleVersion.CryptoMachine;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

// Утильный класс. Сожержит методы, необходимые для работы классов-наследников Machine
class Utils {

    private Utils() {}

    // Создает файл в той же директории, что и исходный файл. Генерирует ему новое имя. Возвращает его путь
    static Path createTargetFile(String sourceFilePath, String insertion) throws IOException {
        Path targetFilePath = Path.of(sourceFilePath.substring(0, sourceFilePath.lastIndexOf(".")) +
                "_"+ insertion + sourceFilePath.substring(sourceFilePath.lastIndexOf(".")));

        try {
            Files.createFile(targetFilePath);
        } catch (FileAlreadyExistsException faee) {
            int count = 1;

            while (Files.exists(targetFilePath)) {
                targetFilePath = Path.of(getNewFileName(targetFilePath.toString(), count++));
            }

            Files.createFile(targetFilePath);
        }

        return targetFilePath;
    }

    // Генерирует новое имя файла на основе старого. Применяется, если файл уже существует
    private static String getNewFileName(String baseString, int num) {
        String firstPart = baseString.toString().substring(0, baseString.toString().lastIndexOf(".")) +
                "(";
        String secondPart = ")" + baseString.toString().substring(baseString.toString().lastIndexOf("."));

        return  firstPart + num + secondPart;
    }

    // Читает содержимое файла, на его основе генерирует мапу "символ" - "кол-во раз, которое он встретился в тексте.
    // На основе мапы строит новую типа "символ" - "процент встречаемости". Возвращает ее.
    static TreeMap<Character, Double> getSignRateMap (String file) throws IOException {
        Path filePath = Path.of(file);

        String fileContent = Files.readString(filePath);
        fileContent = fileContent.toUpperCase(new Locale("ru", "RU"));

        HashMap<Character, Integer> tempMap = new HashMap<>();

        for (char ch: fileContent.toCharArray()) {
            if (tempMap.containsKey(ch)) {
                tempMap.put(ch, tempMap.get(ch) + 1);
            } else {
                tempMap.put(ch, 1);
            }
        }

        TreeMap<Character, Double> signRate = new TreeMap<>();

        for (Map.Entry<Character, Integer> pair: tempMap.entrySet()) {
            signRate.put(pair.getKey(), pair.getValue() * 1.0 / fileContent.length());
        }

        return signRate;
    }

    // Ищет в TreeMap sampleSignRate пару с ближайшим к Double rate значением.
    // Возвращает ключ этой пары
    static Character getCloserChar(Double rate, TreeMap<Character, Double> sampleSignRate) {
        Character key = null;
        Double difference = Double.MAX_VALUE;

        for (Map.Entry<Character, Double> pair: sampleSignRate.entrySet()) {
            if (Math.abs(pair.getValue() - rate) < difference) {
                key = pair.getKey();
            }
        }

        return key;
    }


}
