package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.WrongArgumentException;
import ru.ifmo.lab.utility.Console;

import java.util.Set;

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
     * Метод, удаляющий все элементы коллекции, значения key которых меньше заданного ключа
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (args.isEmpty()) throw new WrongArgumentException();
        try {
            int maxKey = Integer.parseInt(args);
            Set<Integer> allKeys = collectionManager.getKeys();
            for (int key : allKeys){
                if(key < maxKey){
                    collectionManager.removeKey(key);
                }
            }
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
