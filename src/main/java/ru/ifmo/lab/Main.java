package ru.ifmo.lab;

import com.google.gson.JsonSyntaxException;
import ru.ifmo.lab.collection.CollectionChecker;
import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.collection.Flat;
import ru.ifmo.lab.commands.*;
import ru.ifmo.lab.utility.Console;
import ru.ifmo.lab.utility.FileManager;
import ru.ifmo.lab.utility.FlatReader;

import java.util.Arrays;
import java.util.Hashtable;

import static ru.ifmo.lab.utility.JsonParser.decode;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        Console console = new Console();
        FlatReader flatReader = new FlatReader(console);
        FileManager fileManager = new FileManager(console);
        Hashtable<Integer, Flat> collection;

        while (true) {
            if (args.length != 1) fileManager.addFile(args[0]);
            try {
                collection = decode(fileManager.readFromFile());
                break;
            } catch (JsonSyntaxException e){
                console.printCommandError("JSON файл был поврежден и не может быть расшифрован." +
                        "Выберете другой файл");
            }
        }

        //проверка на валидность полей из файла:
        new CollectionChecker(flatReader, console).checkCollection(collection);

        CollectionManager collectionManager = new CollectionManager(console, fileManager, collection,flatReader);
        CommandManager commandManager = new CommandManager(console, collectionManager, flatReader);

        while (true) {
            commandManager.nextCommand();
        }
    }
}