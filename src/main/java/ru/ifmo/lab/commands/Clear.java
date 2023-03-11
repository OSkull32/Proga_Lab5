package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.WrongArgumentException;
import ru.ifmo.lab.utility.Console;

/**
 * Команда, очищающая коллекцию
 */
public class Clear implements Command {

    // Поле, хранящее ссылку на объект класса collectionManager
    private final CollectionManager collectionManager;
    private final Console CONSOLE;

    /**
     * Конструктор класса
     *
     * @param collectionManager хранит ссылку на объект CollectionManager
     */
    public Clear(CollectionManager collectionManager, Console console) {
        this.collectionManager = collectionManager;
        this.CONSOLE = console;
    }

    /**
     * Метод, исполняющий команду. Выводит сообщение о том, что коллекция очищена
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (!args.isEmpty()) throw new WrongArgumentException();
        collectionManager.clear();
        CONSOLE.printCommandTextNext("Коллекция была очищена");
    }

    /**
     * @see Command
     * @return Описание команды
     */
    @Override
    public String getDescription() {
        return "Очищает все элементы коллекции";
    }
}
