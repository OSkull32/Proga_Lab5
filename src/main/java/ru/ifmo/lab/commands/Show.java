package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;

/**
 * Класс команды, которая показывает содержимое коллекции
 */
public class Show implements Command{
    /**
     * Поле, хранящее ссылку на объект класса CollectionManager
     */
    private CollectionManager collectionManager;

    /**
     * Конструктор класса
     *
     * @param collectionManager хранит ссылку на созданный в объекте Application объект CollectionManager
     */
    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, исполняющий команду. Выводит содержимое коллекции
     */
    @Override
    public void execute() {
        collectionManager.show();
    }

    /**
     * @see Command
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "Показывает содержимое всех элементов коллекции";
    }
}
