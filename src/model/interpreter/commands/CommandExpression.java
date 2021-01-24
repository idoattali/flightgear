package model.interpreter.commands;

import model.interpreter.experssions.Expression;

public class CommandExpression implements Expression {
    private Command command;
    private String[] strings;

    public CommandExpression(Command c) {
        this.command = c;
    }

    public Command getC() {
        return command;
    }

    public void setC(Command c) {
        this.command = c;
    }

    public void setS(String[] s) {
        this.strings = s;
    }

    @Override
    public double calculate() {
        command.Execute(strings);
        return 0;
    }
}