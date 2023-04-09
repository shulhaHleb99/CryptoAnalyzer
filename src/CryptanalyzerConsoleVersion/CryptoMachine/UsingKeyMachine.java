package CryptanalyzerConsoleVersion.CryptoMachine;

import CryptanalyzerConsoleVersion.OperatingData;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

class UsingKeyMachine implements Machine {

    private OperatingData opData;
    private static final String ENCRYPTED = "ENCRYPTED";
    private static final String DECRYPTED = "DECRYPTED";

    UsingKeyMachine(OperatingData data) {
        opData = data;
    }

    public void operate() throws IOException {
        int key;
        String insertion;

        if (opData.getOperatingMode() == 1) {
            key = opData.getKey();
            insertion = ENCRYPTED;
        } else {
            key = -opData.getKey();
            insertion = DECRYPTED;
        }

        Path sourceFilePath = Path.of(opData.getOperatedFilePath());
        Path targetFilePath = Utils.createTargetFile(sourceFilePath.toString(), insertion);

        String fileContent = Files.readString(sourceFilePath);
        fileContent = fileContent.toUpperCase(new Locale("ru", "RU"));
        String encryptedFileContent = "";
        for (char ch: fileContent.toCharArray()) {
            encryptedFileContent += SignsContainer.rotate(ch, key);
        }

        Files.writeString(targetFilePath, encryptedFileContent);

        System.out.println("File successfully " + insertion);
        System.out.println("File was written at:");
        System.out.println(targetFilePath);
    }

}
