package ru.ifmo.lab.commands;

import ru.ifmo.lab.exceptions.InvalidCommandException;
import ru.ifmo.lab.exceptions.WrongArgumentException;

/**
 * Интерфейс, реализация которого приведена в командах
 */
public interface Command {
    /**
     * Метод, исполняющий команду
     */
    void execute(String args) throws WrongArgumentException;

    /**
     * Метод, описывающий работу команды
     *
     * @return Возвращает описание команды
     */
    default String getDescription() {
        return "Описание работы команды";
    }
}
