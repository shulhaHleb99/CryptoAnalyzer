package CryptanalyzerConsoleVersion.CryptoMachine;

import CryptanalyzerConsoleVersion.OperatingData;

public class MachineFactory {

    public static Machine getProperMachine(OperatingData opData) {
        Machine machine = null;

        if (opData.getOperatingMode() == 1 || (opData.getOperatingMode() == 2 && opData.getDecryptionMode() == 1)) {
            machine = new UsingKeyMachine(opData);
        } else if (opData.getOperatingMode() == 2 && opData.getDecryptionMode() == 2) {
            machine = new BruteforceMachine(opData);
        } else if (opData.getOperatingMode() == 2 && opData.getDecryptionMode() == 3){
            machine = new StatisticAnalysisMachine(opData);
        } else {
            System.out.println("This case should never happen!");
        }

        return machine;
    }
}
