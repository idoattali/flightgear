package model.interpreter.commands;

import model.interpreter.experssions.ExpressionBulider;

public class PredicateCommand implements Command {
    double check;

    public double getBool() {
        return check;
    }

    @Override
    public void Execute(String[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < (array.length - 1); i++) {
            stringBuilder.append(array[i]);
        }

        check = ExpressionBulider.calc(stringBuilder.toString());
    }
}
