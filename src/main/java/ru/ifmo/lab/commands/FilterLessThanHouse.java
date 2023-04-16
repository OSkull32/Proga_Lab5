package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.collection.Flat;
import ru.ifmo.lab.exceptions.WrongArgumentException;
import ru.ifmo.lab.utility.Console;
import ru.ifmo.lab.utility.FlatReader;

import java.util.Collection;

/**
 * Класс команды "filter_less_than_house".
 *
 * @author Kliodt Vadim
 * @version 1.0
 */

public class FilterLessThanHouse implements Command {

    private final Console console;
    private final FlatReader flatReader;
    private final CollectionManager collectionManager;

    /**
     * Конструирует объект, привязывая его к конкретному объекту {@link CollectionManager}.
     *
     * @param collectionManager указывает на объект {@link CollectionManager}, в котором
     *                          будет вызываться соответствующий метод.
     */
    public FilterLessThanHouse(CollectionManager collectionManager, Console console, FlatReader flatReader) {
        this.collectionManager = collectionManager;
        this.console = console;
        this.flatReader = flatReader;
    }

    /**
     * Метод запускает исполнение команды "filter_less_then_house".
     *
     * @param args Строка, содержащая переданные команде аргументы.
     * @throws WrongArgumentException при неправильном аргументе.
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (!args.isEmpty()) throw new WrongArgumentException();
        try {
            filterLessThanHouse();
        } catch (NumberFormatException e) {
            throw new WrongArgumentException("Аргумент должен быть числом.");
        } catch (ArrayIndexOutOfBoundsException ex) {
            console.printCommandError("Не указаны аргументы команды, необходимо ввести 4 аргумента через пробел");
        }
    }

    private void filterLessThanHouse() {
        console.printCommandTextNext("Введите поля House");
        int year = flatReader.readHouseYear();
        Long numberOfFloors = flatReader.readHouseNumberOfFloors();
        long numberOfFlatsOnFloor = flatReader.readHouseNumberOfFlatsOnFloor();
        Long numberOfLifts = flatReader.readHouseNumberOfLifts();

        Collection<Flat> flatCollection = collectionManager.getCollection().values();
        int countHouse = 0;
        for (Flat flat : flatCollection){
            if (flat.getHouse() == null) continue;
            if (flat.getHouse().getYear() < year
                    && flat.getHouse().getNumberOfFloors() < numberOfFloors
                    && flat.getHouse().getNumberOfFlatsOnFloor() < numberOfFlatsOnFloor
                    && flat.getHouse().getNumberOfLifts() < numberOfLifts){
                countHouse++;
                console.printCommandTextNext(flat.getName() + "; ");
            }
        }
        if (countHouse == 0) console.printCommandTextNext("Таких домов нет.");
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
