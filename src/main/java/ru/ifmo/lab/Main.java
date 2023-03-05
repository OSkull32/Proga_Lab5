package ru.ifmo.lab;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.commands.*;
import ru.ifmo.lab.utility.Console;
import ru.ifmo.lab.utility.FlatReader;

public class Main {
    public static void main(String[] args) {

        Console console = new Console();
        FlatReader flatReader = new FlatReader(console);
        CollectionManager collectionManager = new CollectionManager(console);
        CommandManager commandManager = new CommandManager(console);

        commandManager.addCommand("clear", new Clear(collectionManager));
        //commandManager.addCommand("execute_script", new ExecuteScript());
        commandManager.addCommand("exit", new Exit());
        commandManager.addCommand("filter_less_than_house", new FilterLessThanHouse(collectionManager));
        commandManager.addCommand("help", new Help(commandManager));
        commandManager.addCommand("history", new History(commandManager));
        commandManager.addCommand("info", new Info(collectionManager));
        commandManager.addCommand("update", new Update(collectionManager, console));
        commandManager.addCommand("insert", new Insert(collectionManager, console, flatReader));
        commandManager.addCommand("print_field_ascending_house", new PrintFieldAscendingHouse(collectionManager));
        commandManager.addCommand("remove_all_by_view", new RemoveAllByView(collectionManager));
        commandManager.addCommand("remove_greater_key", new RemoveGreaterKey(collectionManager));
        commandManager.addCommand("remove_key", new RemoveKey(collectionManager));
        commandManager.addCommand("remove_lower_key", new RemoveLowerKey(collectionManager));
        commandManager.addCommand("save", new Save(collectionManager, "jsonfile"));
        commandManager.addCommand("show", new Show(collectionManager));

        while (true) { //TODO исправить это недоразумение (командой exit)
            commandManager.nextCommand();
        }
    }
}