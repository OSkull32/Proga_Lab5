package ru.ifmo.lab.commands;

public interface Command {
    void execute();

    default String getDescription() {
        return "Описание работы команды";
    }
}
