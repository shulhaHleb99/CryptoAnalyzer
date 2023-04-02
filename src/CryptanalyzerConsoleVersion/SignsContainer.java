package CryptanalyzerConsoleVersion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class SignsContainer {

    private static List<Character> signs = new ArrayList<>() {{
        for (int i = 65; i <= 90; i++) {
            add((char) i);
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

    static char rotate(char rotatableChar, int key) {
        int position = signs.indexOf(rotatableChar);
        List<Character> copySigns = new ArrayList<>(signs);
        Collections.rotate(copySigns, key);
        return copySigns.get(position);
    }

}
