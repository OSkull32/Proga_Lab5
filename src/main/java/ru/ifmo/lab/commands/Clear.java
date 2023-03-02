package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.InvalidCommandException;
import ru.ifmo.lab.exceptions.WrongArgumentException;

/**
 * Команда, очищающая коллекцию
 */
public class Clear implements Command {
    /**
     * Поле, хранящее ссылку на объект класса collectionManager
     */
    private CollectionManager collectionManager;

    /**
     * Конструктор класса
     *
     * @param collectionManager хранит ссылку на объект CollectionManager
     */
    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, исполняющий команду. Выводит сообщение о том, что коллекция очищена
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (!args.isEmpty()) throw new WrongArgumentException();
        collectionManager.clear();
        System.out.println("Коллекция была очищена");
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
