package CryptanalyzerConsoleVersion.CryptoMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class SignsContainer {

    // ArrayList содержащий символы, которые могут встречаться в исходном тексте
    private static List<Character> signs = new ArrayList<>() {{
        for (char ch = 'А'; ch != 'Я' + 1; ch++) {
            add(ch);
        }
        add('.');
        add(',');
        add('"');
        add(':');
        add('-');
        add('!');
        add('?');
        add(' ');
    }};

    private SignsContainer() {}

    // Возвращает размер листа
    static int getSignsArraySize() {
        return signs.size();
    }

    // Используя Collections.rotate(), вращает signs. Возвращает символ, полученный смещением rotatableChar в signs на key позиций.
    static char rotate(char rotatableChar, int key) {
        int position = signs.indexOf(rotatableChar);

        if (position == -1) {
            return rotatableChar;
        }

        List<Character> copySigns = new ArrayList<>(signs);

        Collections.rotate(copySigns, key);
        return copySigns.get(position);
    }



}
