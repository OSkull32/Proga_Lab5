package ru.ifmo.lab.commands;

import ru.ifmo.lab.exceptions.WrongArgumentException;

/**
 * Класс команды "history".
 * @author Kliodt Vadim
 * @version 1.0
 */
public class History implements Command{
    private final CommandManager COMMAND_MANAGER;

    /**
     * Конструирует объект, привязывая его к конкретному объекту {@link CommandManager}.
     * @param commandManager указывает на объект {@link CommandManager}, в котором
     *                       будет вызываться соответствующий метод {@link CommandManager#getHistoryList()}.
     */
    public History(CommandManager commandManager){
        this.COMMAND_MANAGER = commandManager;
    }

    /**
     * Выполняет команду "history".
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (!args.isEmpty()) throw new WrongArgumentException();
        COMMAND_MANAGER.getHistoryList();
    }

    /**
     * Метод, описывающий работу команды
     *
     * @return Возвращает описание команды
     */
    @Override
    public String getDescription() {
        return "Возвращает последние 13 использованных команд";
    }
}
