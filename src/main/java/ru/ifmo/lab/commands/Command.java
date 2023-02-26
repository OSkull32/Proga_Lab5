package ru.ifmo.lab.commands;

/**
 * Интерфейс, реализация которого приведена в командах
 */
public interface Command {
    /**
     * Метод, исполняющий команду
     */
    void execute();

    /**
     * Метод, описывающий работу команды
     *
     * @return Возвращает описание команды
     */
    default String getDescription() {
        return "Описание работы команды";
    }
}
