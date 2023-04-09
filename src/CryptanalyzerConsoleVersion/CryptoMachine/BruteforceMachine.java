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
        Map<String, Integer> stringsRatings = new TreeMap<>();

        Path sourceFilePath = Path.of(opData.getOperatedFilePath());
        Path targetFilePath = Utils.createTargetFile(sourceFilePath.toString(), "BRUTEFORCED");

        String fileContent = Files.readString(sourceFilePath).toUpperCase(new Locale("ru", "RU"));

        for (int i = 1; i < SignsContainer.getSignsArraySize(); i++) {
            String newString = "";

            for (char ch: fileContent.toCharArray()) {
                newString += SignsContainer.rotate(ch, i);
            }

            stringsRatings.put(newString, analyzeString(newString));
        }

        String result = null;

        for (Map.Entry<String, Integer> pair: stringsRatings.entrySet()) {
            if (pair.getValue() == maxRating) {
                result = pair.getKey();
            }
        }

        Files.writeString(targetFilePath, result);

        System.out.println("File was decrypted");
        System.out.println("Decrypted file saved at:");
        System.out.println(targetFilePath);

    }

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
