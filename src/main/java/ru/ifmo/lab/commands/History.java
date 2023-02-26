package ru.ifmo.lab.commands;

public class History implements Command{
    private final CommandManager COMMAND_MANAGER;
    public History(CommandManager commandManager){
        this.COMMAND_MANAGER = commandManager;
    }
    @Override
    public void execute(){
        COMMAND_MANAGER.getHistoryList();
    }
}
