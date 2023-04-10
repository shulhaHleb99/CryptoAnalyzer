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

        // Выбор значения ключа, в зависимости от режима работы
        // Отриц. ключ - расшифровка
        // Положительный ключ - шифрование
        if (opData.getOperatingMode() == 1) {
            key = opData.getKey();
            insertion = ENCRYPTED;
        } else {
            key = -opData.getKey();
            insertion = DECRYPTED;
        }

        // Создание объектов Path для исходного файла и файла для записи
        Path sourceFilePath = Path.of(opData.getOperatedFilePath());
        Path targetFilePath = Utils.createTargetFile(sourceFilePath.toString(), insertion);

        // Чтение содержимого одной строкой
        String fileContent = Files.readString(sourceFilePath);
        // Преобразование к верхнему регистру
        fileContent = fileContent.toUpperCase(new Locale("ru", "RU"));
        String encryptedFileContent = "";
        for (char ch: fileContent.toCharArray()) {
            encryptedFileContent += SignsContainer.rotate(ch, key);
        }

        // Запись в целевой файл одной строкой
        Files.writeString(targetFilePath, encryptedFileContent);

        System.out.println("File successfully " + insertion);
        System.out.println("File was written at:");
        System.out.println(targetFilePath);
    }

}
