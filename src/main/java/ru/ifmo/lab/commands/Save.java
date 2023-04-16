package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.WrongArgumentException;
import ru.ifmo.lab.utility.Console;
import ru.ifmo.lab.utility.FileManager;
import ru.ifmo.lab.utility.JsonParser;

/**
 * Класс, который сохраняет коллекцию в файл.
 *
 * @author Kliodt Vadim
 * @version 1.2
 */
public class Save implements Command{
    private final CollectionManager collectionManager;
    private final Console console;
    private final FileManager fileManager;

    /**
     * Конструктор класса
     *
     * @param collectionManager Хранит ссылку на объект CollectionManager.
     */
    public Save(CollectionManager collectionManager, Console console, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.console = console;
        this.fileManager = fileManager;
    }

    /**
     * Метод, сохраняющий коллекцию в указанном файле
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (!args.isEmpty()) throw new WrongArgumentException();
        fileManager.writeToFile(JsonParser.encode(collectionManager.getCollection()));
        console.printCommandTextNext("Коллекция была сохранена.");
    }

    /**
     * Получить описание команды.
     *
     * @return описание команды.
     * @see Command
     */
    @Override
    public String getDescription() {
        return "сохраняет коллекцию в указанный файл";
    }
}