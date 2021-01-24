package model.interpreter;

import java.util.ArrayList;
import java.util.Scanner;

public class LexerSimulator<V> implements ILexer {

    private final Scanner scan;
    private final ArrayList<String[]> lines = new ArrayList<>();


    public LexerSimulator(String v) {
        scan = new Scanner(v);
    }


    public LexerSimulator(V v) {
        scan = new Scanner((Readable) v);
    }

    public ArrayList<String[]> lex() {
        while (scan.hasNextLine() == true) {
            lines.add(scan.nextLine().split(" "));
        }

        return lines;
    }
}