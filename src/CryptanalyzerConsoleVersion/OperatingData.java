package CryptanalyzerConsoleVersion;

import java.io.IOException;

    // Класс, экземпляр которого содержит входные данные
public class OperatingData {

    // Режим работы 1. Шифровка 2. Расшифровка
    private final int operatingMode;

    // Путь к оперируемому файлу
    private final String operatedFilePath;

    //Способ расшифровки 1. На основе ключа 2. Брутфорс 3. Статистический анализ
    private int decryptionMode = 0;

    // Ключ шифрования
    private int key = 0;

    // Путь к файлу примеру текста. Используется в методе стат. анализа
    private String textSamplePath = null;

    OperatingData(int opMode, String opFilePath) throws IOException {
        operatingMode = opMode;
        operatedFilePath = opFilePath;

        // На основе вхоящих данных могут инициализироваться дополнительные поля
        if (opMode == 2) {
            decryptionMode = SelectionUtils.decryptionModeSelection();
        }

        if (decryptionMode == 1 || operatingMode == 1) {
            key = SelectionUtils.getKey();
        }

        if (decryptionMode == 3) {
            textSamplePath = SelectionUtils.getTextSamplePath();
        }

        SelectionUtils.bReader.close();
    }

    public int getOperatingMode() {
        return operatingMode;
    }

    public String getOperatedFilePath() {
        return operatedFilePath;
    }

    public int getDecryptionMode() {
        return decryptionMode;
    }

    public int getKey() {
        return key;
    }

    public String getTextSamplePath() {
        return textSamplePath;
    }

}
