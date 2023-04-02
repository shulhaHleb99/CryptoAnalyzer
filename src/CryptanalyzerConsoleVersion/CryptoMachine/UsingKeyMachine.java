package CryptanalyzerConsoleVersion.CryptoMachine;

import CryptanalyzerConsoleVersion.OperatingData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

class UsingKeyMachine implements Machine {

    OperatingData opData;

    UsingKeyMachine(OperatingData data) {
        opData = data;
    }

    public void operate() throws IOException {
        if (opData.getOperatingMode() == 1) {
            encrypt();
        } else {
            decrypt();
        }
    }

    private void encrypt() throws IOException {
        Path sourceFilePath = Path.of(opData.getOperatedFilePath());

        Path targetFilePath = Path.of(opData.getOperatedFilePath().substring(0, opData.getOperatedFilePath().lastIndexOf(".")) +
                                "_ENCRYPTED" + opData.getOperatedFilePath().substring(opData.getOperatedFilePath().lastIndexOf(".")));

        Files.createFile(targetFilePath);


        String fileContent = Files.readString(sourceFilePath);
        fileContent.toUpperCase(new Locale("ru", "RU"));
        String encryptedFileContent = "";
        for (char ch: fileContent.toCharArray()) {
            encryptedFileContent += SignsContainer.rotate(ch, opData.getKey());
        }

        Files.writeString(targetFilePath, encryptedFileContent);
    }

    private void decrypt() {

    }

}
