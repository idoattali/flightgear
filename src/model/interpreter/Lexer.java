package model.interpreter;

import java.util.Scanner;
import java.util.ArrayList;


public class Lexer implements ILexer {
    private Scanner scan;
    private final ArrayList<String[]> lines = new ArrayList<>();
    private final String[] stringArray;

    public Lexer(String[] strings) {
        stringArray = strings;
    }

    public ArrayList<String[]> lex() {
        if (stringArray != null) {
            for (String string : stringArray) {
                lines.add(string.replaceFirst("=", " = ").replaceFirst("\t", "").split("\\s+"));
            }
        } else {
            while (scan.hasNextLine() == true) {
                lines.add(scan.nextLine().replaceFirst("=", " = ").replaceFirst("\t", "").split("\\s+"));
            }
        }

        return lines;
    }
}