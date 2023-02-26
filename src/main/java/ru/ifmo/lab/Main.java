package ru.ifmo.lab;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.commands.*;
import ru.ifmo.lab.utility.Console;

public class Main {
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager(
                new Console(),
                new Clear(collectionManager),
                new ExecuteScript(),
                new Exit(),
                new FilterLessThanHouse(),
                new Help(),
                new History(),
                new Info(collectionManager),
                new InsertId(),
                new InsertNull(),
                new PrintFieldAscendingHouse(),
                new RemoveAllByView(),
                new RemoveGreaterKey(),
                new RemoveKey(),
                new RemoveLowerKey(),
                new Save(),
                new Show(collectionManager)
        );
        commandManager.nextCommand();
    }
}