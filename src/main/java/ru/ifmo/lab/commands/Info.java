package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;

/**
 * Класс команды, которая выводит информацию о коллеции
 */
public class Info implements Command{
    /**
     * Поле, хранящее ссылку на объект класса CollectionManager
     */
    private CollectionManager collectionManager;

    /**
     * Конструктор класса
     *
     * @param collectionManager хранит ссылку на созданный в объекте Application объект CollectionManager
     */
    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, исполняющий команду. Выводит описание коллекции HashTable
     */
    @Override
    public void execute() {
        collectionManager.info();
    }

    /**
     * @see Command
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "Команда выводит информацию о коллекции";
    }
}
