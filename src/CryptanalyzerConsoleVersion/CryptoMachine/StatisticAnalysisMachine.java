package CryptanalyzerConsoleVersion.CryptoMachine;

import CryptanalyzerConsoleVersion.OperatingData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class StatisticAnalysisMachine implements Machine {

    OperatingData opData;

    private TreeMap<Character, Double> sampleSignRate;

    private TreeMap<Character, Double> sourceSignRate;

    private HashMap<Character, Character> matchedChars;

    StatisticAnalysisMachine(OperatingData data) {
        opData = data;
    }

    public void operate() throws IOException {

        // Создание объектов Path для исходного файла и файла для записи
        Path sourceFilePath = Path.of(opData.getOperatedFilePath());
        Path targetFilePath = Utils.createTargetFile(sourceFilePath.toString(), "_ENCRYPTEDStat");

        // Инициализация TreeMap-ов, содержащих пары "символ" - "встречаемость"
        sampleSignRate = Utils.getSignRateMap(opData.getTextSamplePath());
        sourceSignRate = Utils.getSignRateMap(opData.getOperatedFilePath());

        // Создание HashMap типа "символ" - "символ" путем соотнесения чаров, близких по встречаемости в обеих TreeMap-ах
        matchedChars = new HashMap<>();

        for (Map.Entry<Character, Double> pair: sourceSignRate.entrySet()) {
            matchedChars.put(pair.getKey(), Utils.getCloserChar(pair.getValue(), sampleSignRate));
        }

        // Замена исходных символов близкими по встречаемости из matchedChars
        StringBuilder decryptedFileContent = new StringBuilder("");
        // Чтение содержимого одной строкой
        String fileContent = Files.readString(Path.of(opData.getOperatedFilePath()));
        // Преобразование к верхнему регистру
        fileContent = fileContent.toUpperCase(new Locale("ru", "RU"));

        for (char ch: fileContent.toCharArray()) {
            decryptedFileContent.append(matchedChars.get(ch));
        }

        // Запись в целевой файл одной строкой
        Files.writeString(targetFilePath, decryptedFileContent);

        System.out.println("File successfully DECRYPTED");
        System.out.println("File was written at:");
        System.out.println(targetFilePath);
    }
}
