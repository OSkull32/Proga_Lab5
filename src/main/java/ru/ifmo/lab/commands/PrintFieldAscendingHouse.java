package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.WrongArgumentException;

/**
 * Класс команды "print_field_ascending_house".
 *
 * @author Kliodt Vadim
 * @version 1.0
 */
public class PrintFieldAscendingHouse implements Command {
    private final CollectionManager COLLECTION_MANAGER;

    /**
     * Конструирует объект, првязывая его к конкретному объекту {@link CollectionManager}.
     *
     * @param collectionManager указывет на объект {@link CollectionManager}, в котором
     *                          будет вызываться соответствующий
     *                          метод {@link CollectionManager#printFieldAscendingHouse()}.
     */
    public PrintFieldAscendingHouse(CollectionManager collectionManager) {
        this.COLLECTION_MANAGER = collectionManager;
    }

    /**
     * Метод запускает исполнение команды "print_field_ascending_house".
     *
     * @param args Сторка, содержащая переданные команде аргументы.
     * @throws WrongArgumentException - если команде был передан аргумент.
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (!args.isEmpty()) throw new WrongArgumentException();
        COLLECTION_MANAGER.printFieldAscendingHouse();
    }

    /**
     * Получить описание команды.
     *
     * @return описание команды.
     */
    @Override
    public String getDescription() {
        return "выводит значения поля house всех элементов в порядке возрастания";
    }
}
