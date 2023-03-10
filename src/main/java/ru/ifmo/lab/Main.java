package ru.ifmo.lab;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.collection.Flat;
import ru.ifmo.lab.commands.*;
import ru.ifmo.lab.utility.Console;
import ru.ifmo.lab.utility.FileManager;
import ru.ifmo.lab.utility.FlatReader;

import java.util.Hashtable;

import static ru.ifmo.lab.utility.JsonParser.decode;

public class Main {
    public static void main(String[] args) {

        ExecuteScript.Script script = new ExecuteScript.Script();
        Console console = new Console();
        FlatReader flatReader = new FlatReader(console);
        FileManager fileManager = new FileManager(console);
        //TODO сделать проверку файлов
        Hashtable<Integer, Flat> collection = decode(fileManager.readFromFile());
        CollectionManager collectionManager = new CollectionManager(console, fileManager, collection);
        CommandManager commandManager = new CommandManager(console);

        commandManager.addCommand("clear", new Clear(collectionManager));
        commandManager.addCommand("execute_script", new ExecuteScript(script, commandManager));
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
        commandManager.addCommand("save", new Save(collectionManager));
        commandManager.addCommand("show", new Show(collectionManager));

        while (true) { //TODO исправить это недоразумение (командой exit)
            commandManager.nextCommand();
        }
    }
}