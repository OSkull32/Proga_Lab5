package ru.ifmo.lab;

import com.google.gson.JsonSyntaxException;
import ru.ifmo.lab.collection.CollectionChecker;
import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.collection.Flat;
import ru.ifmo.lab.commands.*;
import ru.ifmo.lab.utility.Console;
import ru.ifmo.lab.utility.FileManager;
import ru.ifmo.lab.utility.FlatReader;
import ru.ifmo.lab.utility.UserConsole;

import java.util.Hashtable;

import static ru.ifmo.lab.utility.JsonParser.decode;

public class Main {
    public static void main(String[] args) {
        Console console = new UserConsole();
        FlatReader flatReader = new FlatReader(console);
        FileManager fileManager = new FileManager(console);

        fileManager.addFile(args.length == 0 ? null : args[0]);

        Hashtable<Integer, Flat> collection = getCollectionFromFile(console, fileManager);

        //проверка на валидность полей из файла:
        (new CollectionChecker(flatReader, console)).checkCollection(collection);

        CollectionManager collectionManager = new CollectionManager(console, collection,flatReader);
        CommandManager commandManager = new CommandManager(console, collectionManager, flatReader, fileManager);

        while (true) {
            commandManager.nextCommand();
        }
    }

    private static Hashtable<Integer, Flat> getCollectionFromFile(Console console, FileManager fileManager) {
        while (true) {
            try {
                return decode(fileManager.readFromFile());
            } catch (JsonSyntaxException e){
                console.printCommandError("JSON файл был поврежден и не может быть расшифрован. " +
                        "Выберете другой файл");
                fileManager.addFile();
            }
        }
    }
}