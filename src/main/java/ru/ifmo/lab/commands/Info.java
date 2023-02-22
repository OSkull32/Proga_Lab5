package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;

public class Info implements Command{
    private CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.info();
    }

    @Override
    public String getDescription() {
        return "Команда выводит информацию о коллекции";
    }
}
