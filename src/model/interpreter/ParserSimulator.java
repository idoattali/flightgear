package model.interpreter;

import model.interpreter.commands.*;

import java.util.ArrayList;
import java.util.HashMap;


public class ParserSimulator implements IParser {

    public static HashMap<String, Var> symTbl = new HashMap<>();
    private final HashMap<String, Command> cmdTbl = new HashMap<>();
    private final ArrayList<String[]> lines;


    public ParserSimulator(ArrayList<String[]> lines) {
        this.lines = lines;
        cmdTbl.put("openDataServer", new OpenDataServer());
        cmdTbl.put("connect", new ConnectCommand());
        cmdTbl.put("while", new WhileCommand());
    }

    public void parse() {
        for (int i = 0; i < lines.size(); i++) {
            Command c = cmdTbl.get(lines.get(i)[0]);

            if (c != null) {
                if (lines.get(i)[0].equals("while")) {
                    int index = i;

                    while (!lines.get(i)[0].equals("}")) {
                        i++;
                    }

                    this.parseCondition(new ArrayList<String[]>(lines.subList(index, i)));
                }
            }

            c.Execute(lines.get(i));
        }
    }

    private void parseCondition(ArrayList<String[]> array) {
        ArrayList<Command> comds = new ArrayList<>();
        int i = 0;
        String[] tmp = array.get(i);
        ConditionalCommand c = (ConditionalCommand) cmdTbl.get(tmp[0]);

        i++;
        for (; i < array.size(); i++) {
            Command com = cmdTbl.get(array.get(i)[0]);
            if (com != null) {
                if (array.get(i)[0].equals("while")) {
                    int index = i;

                    while (array.get(i)[0] != "}") {
                        i++;
                    }

                    this.parseCondition(new ArrayList<String[]>(array.subList(index, i)));
                }
            }

            comds.add(com);
        }
    }
}