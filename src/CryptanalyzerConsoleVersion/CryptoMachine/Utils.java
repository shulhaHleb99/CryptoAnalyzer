package CryptanalyzerConsoleVersion.CryptoMachine;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

class Utils {

    private Utils() {}

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

    private static String getNewFileName(String baseString, int num) {
        String firstPart = baseString.toString().substring(0, baseString.toString().lastIndexOf(".")) +
                "(";
        String secondPart = ")" + baseString.toString().substring(baseString.toString().lastIndexOf("."));

        return  firstPart + num + secondPart;
    }

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
