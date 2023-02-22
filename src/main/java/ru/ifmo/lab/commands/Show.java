package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;

public class Show implements Command{
    private CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.show();
    }

    @Override
    public String getDescription() {
        return "Показывает содержимое всех элементов коллекции";
    }
}
