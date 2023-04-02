package CryptanalyzerConsoleVersion;

import java.io.IOException;

public class OperatingData {

    private final int operatingMode;

    private final String operatedFilePath;

    private int decryptionMode = 0;

    private int key = 0;

    private String textSamplePath = null;

    OperatingData(int opMode, String opFilePath) throws IOException {
        operatingMode = opMode;
        operatedFilePath = opFilePath;

        if (opMode == 2) {
            decryptionMode = SelectionUtils.decryptionModeSelection();
        }

        if (decryptionMode == 1 || operatingMode == 1) {
            key = SelectionUtils.getKey();
        }

        if (decryptionMode == 3) {
            textSamplePath = SelectionUtils.getTextSamplePath();
        }
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
