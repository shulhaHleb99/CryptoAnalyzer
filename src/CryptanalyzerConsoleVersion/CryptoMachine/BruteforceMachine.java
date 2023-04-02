package CryptanalyzerConsoleVersion.CryptoMachine;

import CryptanalyzerConsoleVersion.OperatingData;

class BruteforceMachine implements Machine {

    OperatingData opData;

    BruteforceMachine(OperatingData data) {
        opData = data;
    }
}
