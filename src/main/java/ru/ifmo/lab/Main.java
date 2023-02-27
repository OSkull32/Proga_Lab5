package ru.ifmo.lab;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.commands.*;
import ru.ifmo.lab.utility.Console;

public class Main {
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager(new Console());
        CommandManager commandManager = new CommandManager(new Console());

        commandManager.addCommand("clear", new Clear(collectionManager));
        commandManager.addCommand("execute_script", new ExecuteScript());
        commandManager.addCommand("exit", new Exit());
        commandManager.addCommand("filter_less_than_house", new FilterLessThanHouse());
        commandManager.addCommand("help", new Help());
        commandManager.addCommand("history", new History(commandManager));
        commandManager.addCommand("info", new Info(collectionManager));
        commandManager.addCommand("update", new Update());
        commandManager.addCommand("insert", new Insert());
        commandManager.addCommand("print_field_ascending_house", new PrintFieldAscendingHouse());
        commandManager.addCommand("remove_all_by_view", new RemoveAllByView());
        commandManager.addCommand("remove_greater_key", new RemoveGreaterKey());
        commandManager.addCommand("remove_key", new RemoveKey());
        commandManager.addCommand("remove_lower_key", new RemoveLowerKey());
        commandManager.addCommand("save", new Save());
        commandManager.addCommand("show", new Show(collectionManager));

        while (true) { //TODO исправить это недоразумение (командой exit)
            commandManager.nextCommand();
        }
    }
}