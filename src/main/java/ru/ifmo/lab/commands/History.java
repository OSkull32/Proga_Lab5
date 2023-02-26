package ru.ifmo.lab.commands;

/**
 * Класс команды "history".
 * @author Kliodt Vadim
 * @version 1.0
 */
public class History implements Command{
    private final CommandManager COMMAND_MANAGER;

    /**
     * Конструирует объект, првязывая его к конкретному объекту {@link CommandManager}.
     * @param commandManager указывет на объект {@link CommandManager}, в котором
     *                       будет вызываться соответствующий метод {@link CommandManager#getHistoryList()}.
     */
    public History(CommandManager commandManager){
        this.COMMAND_MANAGER = commandManager;
    }

    /**
     * Выполняет команду "history".
     */
    @Override
    public void execute(){
        COMMAND_MANAGER.getHistoryList();
    }
}
