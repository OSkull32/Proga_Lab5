package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.WrongArgumentException;
import ru.ifmo.lab.utility.FileManager;

/**
 * Класс, который сохраняет коллекцию в файл
 */
public class Save implements Command{
    private final FileManager FILE_MANAGER;
    private CollectionManager collectionManager;

    /**
     * Конструктор класса
     *
     * @param collectionManager Хранит ссылку на объект CollectionManager.
     */
    public Save(CollectionManager collectionManager, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.FILE_MANAGER = fileManager;
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
