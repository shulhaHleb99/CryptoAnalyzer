package CryptanalyzerConsoleVersion;

import CryptanalyzerConsoleVersion.CryptoMachine.Machine;
import CryptanalyzerConsoleVersion.CryptoMachine.MachineFactory;

import java.io.IOException;

public class Main {

    private static OperatingData data;

    private static Machine machine;

    public static void main(String[] args) throws IOException {

        data = new OperatingData(SelectionUtils.modeSelection(), SelectionUtils.getOperatedFilePath());
        machine = MachineFactory.getProperMachine(data);
        machine.operate();

    }

}







