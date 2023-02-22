package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;

public class Clear implements Command{
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager=collectionManager;
    }
    @Override
    public void execute() {
        collectionManager.clear();
        System.out.println("Коллекция была очищена");
    }

    @Override
    public String getDescription() {
        return "Очищает все элементы коллекции";
    }
}
