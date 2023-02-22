package ru.ifmo.lab.commands;

public class Clear implements Command{
    @Override
    public void execute() {
    }

    @Override
    public String getDescription() {
        return "Очищает все элементы коллекции";
    }
}
