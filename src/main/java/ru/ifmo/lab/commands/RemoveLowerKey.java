package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.WrongArgumentException;
/**
 * Класс команды, которая удаляет элементы меньше заданного ключа
 */
public class RemoveLowerKey implements Command{
    private CollectionManager collectionManager;

    /**
     * Конструктор класса.
     *
     * @param collectionManager Хранит ссылку на созданный в объекте Application объект CollectionManager.
     */
    public RemoveLowerKey(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, удаляющий все элементы коллекции, значения ключа которых меньше заданного
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (args.isEmpty()) throw new WrongArgumentException();
        try {
            collectionManager.removeLowerKey(Integer.parseInt(args));
            System.out.println("Элементы коллекции были удалены.");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Не указан аргумент команды");
        } catch (NumberFormatException ex) {
            System.out.println("Формат аргумента не соответствует целочисленному " + ex.getMessage());
        }
    }

    /**
     * @see Command
     * @return Возращает описание команды.
     */
    @Override
    public String getDescription() {
        return "удаляет все элементы коллекции, значение id которых меньше указанного ключа";
    }
}
