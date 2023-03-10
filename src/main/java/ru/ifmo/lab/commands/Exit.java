package ru.ifmo.lab.commands;

import ru.ifmo.lab.exceptions.WrongArgumentException;

/**
 * Класс команды, которая завершает работу программы
 */
public class Exit implements Command{
    /**
     * Метод, исполняющий команду. Выводит сообщение о завершении работы программы
     */
    @Override
    public void execute(String args) throws WrongArgumentException{
        if (!args.isEmpty()) throw new WrongArgumentException();
        System.out.println("Завершение работы программы");
        System.exit(0);
    }

    /**
     * @see Command
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "Команда завершает программу";
    }
}
