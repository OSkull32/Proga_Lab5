package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.WrongArgumentException;

/**
 * Класс команды "filter_less_than_house".
 *
 * @author Kliodt Vadim
 * @version 1.0
 */

public class FilterLessThanHouse implements Command {
    private final CollectionManager COLLECTION_MANAGER;

    /**
     * Конструирует объект, првязывая его к конкретному объекту {@link CollectionManager}.
     *
     * @param collectionManager указывет на объект {@link CollectionManager}, в котором
     *                          будет вызываться соответствующий
     *                          метод {@link CollectionManager#filterLessThanHouse(int numberOfFloors)}.
     */
    public FilterLessThanHouse(CollectionManager collectionManager) {
        this.COLLECTION_MANAGER = collectionManager;
    }

    /**
     * Метод запускает исполнение команды "filter_less_then_house".
     *
     * @param args Сторка, содержащая переданные команде аргументы.
     * @throws WrongArgumentException при неправильном аргументе.
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (args.isEmpty()) throw new WrongArgumentException("Требуется аргумент.");
        try {
            COLLECTION_MANAGER.filterLessThanHouse(Integer.parseInt(args));
        } catch (NumberFormatException e) {
            throw new WrongArgumentException("Аргумент должен быть числом.");
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
