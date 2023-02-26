package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;

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
     * @param collectionManager хранит ссылку на созданный в объекте Application объект CollectionManager
     */
    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, исполняющий команду. Выводит сообщение о том, что коллекция очищена
     */
    @Override
    public void execute() {
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
