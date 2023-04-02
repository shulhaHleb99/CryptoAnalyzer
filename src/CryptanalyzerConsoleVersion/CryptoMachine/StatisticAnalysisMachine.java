package CryptanalyzerConsoleVersion.CryptoMachine;

import CryptanalyzerConsoleVersion.OperatingData;

import java.io.IOException;

class StatisticAnalysisMachine implements Machine {

    OperatingData opData;

    StatisticAnalysisMachine(OperatingData data) {
        opData = data;
    }

    public void operate() throws IOException {}
}
