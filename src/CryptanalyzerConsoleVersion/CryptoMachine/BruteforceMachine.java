package CryptanalyzerConsoleVersion.CryptoMachine;

import CryptanalyzerConsoleVersion.OperatingData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class BruteforceMachine implements Machine {

    private OperatingData opData;

    private static int maxRating = 0;

    BruteforceMachine(OperatingData data) {
        opData = data;
    }

    public void operate() throws IOException {
        // TreeMap, содержащий варианты сдвига строки на разное кол-во позиций - "рейтинг" варианта
        // Рейтинг строки на основе анализа кода с использованием regex-выражений
        Map<String, Integer> stringsRatings = new TreeMap<>();

        // Создание объектов Path для исходного файла и файла для записи
        Path sourceFilePath = Path.of(opData.getOperatedFilePath());
        Path targetFilePath = Utils.createTargetFile(sourceFilePath.toString(), "BRUTEFORCED");

        // Чтение содержимого одной строкой
        String fileContent = Files.readString(sourceFilePath).toUpperCase(new Locale("ru", "RU"));

        // Получение вариантов строк путем сдвига на разное кол-во позиций
        for (int i = 1; i < SignsContainer.getSignsArraySize(); i++) {
            String newString = "";

            for (char ch: fileContent.toCharArray()) {
                newString += SignsContainer.rotate(ch, i);
            }

            stringsRatings.put(newString, analyzeString(newString));
        }

        String result = null;

        // Выбор варианта с максимальным рейтингом (наиболее вероятного варанта расшифрованной строки)
        for (Map.Entry<String, Integer> pair: stringsRatings.entrySet()) {
            if (pair.getValue() == maxRating) {
                result = pair.getKey();
            }
        }

        // Запись в целевой файл одной строкой
        Files.writeString(targetFilePath, result);

        System.out.println("File was decrypted");
        System.out.println("Decrypted file saved at:");
        System.out.println(targetFilePath);

    }


    // Метод, определяющий "рейтинг" сток, путем анализа совпадений с regex-выражениями
    private int analyzeString(String analyzedString) {
        int rating = 0;

        String[] regexes = {"[А-Я]\\s[А-Я]", "^[А-Я]", "[А-Я]\\.\\s[А-Я]", "[А-Я],\\s[А-Я]", "[А-Я]-[А-Я]", "[А-Я]!\\s", "[А-Я]\\?\\s", "[А-Я]:\\s", "\\s\"[А-Я]"};

        for (String regex : regexes) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(analyzedString);

            while (matcher.find()) rating++;
        }

        if (maxRating < rating) {
            maxRating = rating;
        }

        return rating;
    }
}
