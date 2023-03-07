package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.WrongArgumentException;

/**
 * Класс, который сохраняет коллекцию в файл.
 *
 * @author Kliodt Vadim
 * @version 1.0
 */
public class Save implements Command{
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса
     *
     * @param collectionManager Хранит ссылку на объект CollectionManager.
     */
    public Save(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, сохраняющий коллекцию в указанном файле
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (!args.isEmpty()) throw new WrongArgumentException();
        collectionManager.save();
        System.out.println("Коллекция была сохранена.");
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
