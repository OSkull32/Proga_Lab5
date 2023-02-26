package ru.ifmo.lab.commands;

/**
 * Класс команды, которая завершает работу программы
 */
public class Exit implements Command{
    /**
     * Конструктор класса
     */
    public Exit(){
    }

    /**
     * Метод, исполняющий команду. Выводит сообщение о завершении работы программы
     */
    @Override
    public void execute() {
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
