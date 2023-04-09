package CryptanalyzerConsoleVersion;

import CryptanalyzerConsoleVersion.CryptoMachine.Machine;
import CryptanalyzerConsoleVersion.CryptoMachine.MachineFactory;

import java.io.IOException;

public class Main {

    // Содержит данные, на основе которых работает программа
    private static OperatingData data;

    // Экземпляр класса Machine, подбирается на основе входных данных
    private static Machine machine;

    public static void main(String[] args) throws IOException {

        // Сборка входных данных в экземпляр класса OperatingDate
        data = new OperatingData(SelectionUtils.modeSelection(), SelectionUtils.getOperatedFilePath());

        // Подбор экземпляра Machine
        machine = MachineFactory.getProperMachine(data);

        // Запуск основного метода класса Machine
        machine.operate();

    }

}

// Программа работает только с русским текстом
// Логика расшифровки методом стат. анализа реализована, хотя по факту, она не работает
// Попытался реализовать в пакете CryptoMachine паттерн Factory





