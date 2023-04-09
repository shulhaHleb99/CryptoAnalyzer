package CryptanalyzerConsoleVersion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Утильный класс. Содержит методы получения входных данных
class SelectionUtils {

    public static BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

    private SelectionUtils() {
    }

    // Метод выбора режима работы
    static int modeSelection() throws IOException {

        System.out.println("Please, select the operating mode (Enter point number):" +
                "\n 1. Encryption mode" +
                "\n 2. Decryption mode");


        while (true) {

            String temp = bReader.readLine();

            if (temp.equals("1")) {
                System.out.println("Encryption mode selected");
                return 1;
            } else if (temp.equals("2")) {
                System.out.println("Decryption mode selected");
                return 2;
            } else {
                System.out.println("Invalid value entered! Please, try again.");
                continue;
            }
        }
    }

    // Метод введения пути оперируемого файла
    static String getOperatedFilePath() throws IOException {
        String filePath = null;
        System.out.println("Please, enter path of file to be operated");

        while (true) {
            filePath = bReader.readLine();

            if (Files.exists(Paths.get(filePath))) {
                break;
            } else {
                System.out.println("Invalid filepath entered! Please, try again.");
            }
        }

        return filePath;
    }


    // Метод выбора способа расшифровки
    static int decryptionModeSelection() throws IOException {
        String temp = null;

        final String USING_KEY = "Using key";
        final String BRUTEFORCE = "Bruteforce";
        final String STATISTIC_ANALYSIS = "Statistic analysis";

        System.out.println("Please, select the decryption mode (Enter point number):" +
                "\n 1. " + USING_KEY +
                "\n 2. " + BRUTEFORCE +
                "\n 3. " + STATISTIC_ANALYSIS);

        while (true) {

            temp = bReader.readLine();

            if (temp.equals("1")) {
                System.out.println(USING_KEY + " decryption mode selected");
                return 1;
            } else if (temp.equals("2")) {
                System.out.println(BRUTEFORCE + " decryption mode selected");
                return 2;
            } else if (temp.equals("3")) {
                System.out.println(STATISTIC_ANALYSIS + " decryption mode selected");
                return 3;
            } else {
                System.out.println("Invalid value entered! Please, try again.");
            }
        }
    }

    // Выбор ключа
    static int getKey() throws IOException {
        int key;
        System.out.println("Please, enter the encryption key");

        while (true) {
            String temp = bReader.readLine();

            try {
                key = Integer.parseInt(temp);
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid value entered! Please, try again.");
                continue;
            }

            return key;
        }
    }

    // Метод получения файла примера текста
    static String getTextSamplePath() throws IOException {
        String samplePath = null;
        System.out.print("Please, enter path of the text sample");

        while (true) {

            samplePath = bReader.readLine();

            if (Files.exists(Paths.get(samplePath))) {
                break;
            } else {
                System.out.println("Invalid filepath! Please, try again.");
            }

        }

        return samplePath;
    }

}
