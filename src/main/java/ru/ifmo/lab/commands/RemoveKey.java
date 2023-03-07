package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.WrongArgumentException;

/**
 * Класс команды, которая удаляет элемент
 */
public class RemoveKey implements Command{
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса.
     *
     * @param collectionManager Хранит ссылку на объект CollectionManager.
     */
    public RemoveKey(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, удаляющий элемент коллекции, по значению ключа
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (args.isEmpty()) throw new WrongArgumentException();
        try {
            if (collectionManager.containsKey(Integer.parseInt(args))) {
                collectionManager.removeKey(Integer.parseInt(args));
                System.out.println("Элемент коллекции был удален.");
            } else System.out.println("Данного элемента коллекции не существует");
        } catch (IndexOutOfBoundsException ex) {
            System.err.println("Не указаны аргументы команды");
        } catch (NumberFormatException ex) {
            System.err.println("Формат аргумента не соответствует целочисленному " + ex.getMessage());
        }
    }

    /**
     * @return Возвращает описание команды
     * @see Command
     */
    @Override
    public String getDescription() {
        return "удаляет элемент с указанным ключом";
    }
}
