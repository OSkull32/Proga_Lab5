package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.WrongArgumentException;
import ru.ifmo.lab.utility.Console;

/**
 * Класс команды, удаляющий элементы, у которых id меньше заданного ключа
 */
public class RemoveLowerKey implements Command{
    private final CollectionManager collectionManager;
    private final Console CONSOLE;

    /**
     * Конструктор класса.
     *
     * @param collectionManager Хранит ссылку на объект CollectionManager.
     */
    public RemoveLowerKey(CollectionManager collectionManager, Console console) {
        this.collectionManager = collectionManager;
        this.CONSOLE = console;
    }

    /**
     * Метод, удаляющий все элементы коллекции, значения id которых меньше заданного ключа
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (args.isEmpty()) throw new WrongArgumentException();
        try {
            collectionManager.removeLowerKey(Integer.parseInt(args));
            CONSOLE.printCommandTextNext("Элементы коллекции были удалены.");
        } catch (IndexOutOfBoundsException ex) {
            CONSOLE.printCommandError("Не указан аргумент команды");
        } catch (NumberFormatException ex) {
            CONSOLE.printCommandError("Формат аргумента не соответствует целочисленному " + ex.getMessage());
        }
    }

    /**
     * @see Command
     * @return описание команды.
     */
    @Override
    public String getDescription() {
        return "удаляет все элементы коллекции, значение id которых меньше указанного ключа";
    }
}
