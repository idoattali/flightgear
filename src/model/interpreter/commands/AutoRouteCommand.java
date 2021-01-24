package model.interpreter.commands;

import model.Model;

public class AutoRouteCommand implements Command {
    @Override
    public void Execute(String[] array) { Model.turn = false; }
}