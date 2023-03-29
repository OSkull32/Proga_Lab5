package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.WrongArgumentException;
import ru.ifmo.lab.utility.Console;

/**
 * Класс команды "filter_less_than_house".
 *
 * @author Kliodt Vadim
 * @version 1.0
 */

public class FilterLessThanHouse implements Command {

    private final Console console;

    private final CollectionManager COLLECTION_MANAGER;

    /**
     * Конструирует объект, привязывая его к конкретному объекту {@link CollectionManager}.
     *
     * @param collectionManager указывает на объект {@link CollectionManager}, в котором
     *                          будет вызываться соответствующий
     *                          метод {@link CollectionManager#filterLessThanHouse(int year, Long numberOfFloors, long numberOfFlatsOnFloor, Long numberOfLifts)}.
     */
    public FilterLessThanHouse(CollectionManager collectionManager, Console console) {
        this.COLLECTION_MANAGER = collectionManager;
        this.console = console;
    }

    /**
     * Метод запускает исполнение команды "filter_less_then_house".
     *
     * @param args Строка, содержащая переданные команде аргументы.
     * @throws WrongArgumentException при неправильном аргументе.
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        String[] arguments = args.split("\\s");
        try {
            COLLECTION_MANAGER.filterLessThanHouse(Integer.parseInt(arguments[0]), Long.parseLong(arguments[1]), Long.parseLong(arguments[2]), Long.parseLong(arguments[3]));
        } catch (NumberFormatException e) {
            throw new WrongArgumentException("Аргумент должен быть числом.");
        } catch (ArrayIndexOutOfBoundsException ex) {
            console.printCommandError("Не указаны аргументы команды, необходимо ввести 4 аргумента через пробел");
        }
    }

    /**
     * Получить описание команды.
     *
     * @return описание команды.
     */
    @Override
    public String getDescription() {
        return "Вывести элементы, значение поля house которых меньше заданного";
    }
}
