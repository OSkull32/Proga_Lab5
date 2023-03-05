package ru.ifmo.lab.commands;

import ru.ifmo.lab.exceptions.WrongArgumentException;

/**
 * Интерфейс, реализация которого приведена в командах.
 */
public interface Command {
    /**
     * Метод, исполняющий команду.
     * @param args Сторка, содержащая переданные команде аргументы.
     * @throws WrongArgumentException если аргумент был введен некорректно / требовался,
     * но не был введен / не требовался, но был введен.
     */
    void execute(String args) throws WrongArgumentException;

    /**
     * Метод, описывающий работу команды
     * @return Возвращает описание команды
     */
    default String getDescription() {
        return "Описание работы команды";
    }
}
