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

        //
        Path sourceFilePath = Path.of(opData.getOperatedFilePath());
        Path targetFilePath = Utils.createTargetFile(sourceFilePath.toString(), "_ENCRYPTEDStat");

        sampleSignRate = Utils.getSignRateMap(opData.getTextSamplePath());
        sourceSignRate = Utils.getSignRateMap(opData.getOperatedFilePath());

        matchedChars = new HashMap<>();

        for (Map.Entry<Character, Double> pair: sourceSignRate.entrySet()) {
            matchedChars.put(pair.getKey(), Utils.getCloserChar(pair.getValue(), sampleSignRate));
        }

        StringBuilder decryptedFileContent = new StringBuilder("");
        String fileContent = Files.readString(Path.of(opData.getOperatedFilePath()));
        fileContent = fileContent.toUpperCase(new Locale("ru", "RU"));

        for (char ch: fileContent.toCharArray()) {
            decryptedFileContent.append(matchedChars.get(ch));
        }

        Files.writeString(targetFilePath, decryptedFileContent);

        System.out.println("File successfully DECRYPTED");
        System.out.println("File was written at:");
        System.out.println(targetFilePath);
    }
}
