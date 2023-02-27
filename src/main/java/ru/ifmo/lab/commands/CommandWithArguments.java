package ru.ifmo.lab.commands;

/**
 * Интерфейс, реализуемый командами с аргументами
 */
public interface CommandWithArguments extends Command{
    /**
     * Метод, получающий аргументы команды.
     *
     * @param arguments Аргументы команды.
     */
    void getCommandArguments(String[] arguments);
}
