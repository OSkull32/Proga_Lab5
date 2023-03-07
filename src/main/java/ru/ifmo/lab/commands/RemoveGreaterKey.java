package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.WrongArgumentException;
/**
 * Класс команды, удаляющая элементы id которых, больше заданного ключа
 */
public class RemoveGreaterKey implements Command{
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса.
     *
     * @param collectionManager Хранит ссылку на объект CollectionManager.
     */
    public RemoveGreaterKey(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, удаляющий все элементы коллекции, значения id которых больше заданного ключа
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (args.isEmpty()) throw new WrongArgumentException();
        try {
            collectionManager.removeGreaterKey(Integer.parseInt(args));
            System.out.println("Элементы коллекции были удалены.");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Не указан аргумент команды");
        } catch (NumberFormatException ex) {
            System.out.println("Формат аргумента не соответствует целочисленному " + ex.getMessage());
        }
    }

    /**
     * @see Command
     * @return описание команды.
     */
    @Override
    public String getDescription() {
        return "удаляет все элементы коллекции, значение id которых больше указанного ключа";
    }
}
