package CryptanalyzerConsoleVersion.CryptoMachine;

import CryptanalyzerConsoleVersion.OperatingData;

import java.io.IOException;

// Интерфейс, объединяющий BruteforceMachine, UsingKeyMachine, StaticAnalysisMachine
public interface Machine {

    // Метод, в котором содержится основная логика.
    public void operate() throws IOException;
}
