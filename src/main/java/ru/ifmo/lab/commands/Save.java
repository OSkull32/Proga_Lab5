package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.WrongArgumentException;

/**
 * Класс, который сохраняет коллекцию в файл
 */
public class Save implements Command{
    private String inputFile;
    private CollectionManager collectionManager;

    /**
     * Конструктор класса
     *
     * @param collectionManager Хранит ссылку на объект CollectionManager.
     * @param inputFile         Хранит адрес файла, куда следует сохранить элементы коллекции.
     */
    public Save(CollectionManager collectionManager, String inputFile) {
        this.collectionManager = collectionManager;
        this.inputFile = inputFile;
    }

    /**
     * Метод, сохраняющий коллекцию в указанном файле
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (!args.isEmpty()) throw new WrongArgumentException();
        collectionManager.save(inputFile);
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
