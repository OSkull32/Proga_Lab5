package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.collection.Furnish;
import ru.ifmo.lab.collection.View;
import ru.ifmo.lab.exceptions.InvalidValueException;
import ru.ifmo.lab.exceptions.WrongArgumentException;
import ru.ifmo.lab.utility.Console;
import ru.ifmo.lab.utility.FlatReader;

import java.util.Arrays;
import java.util.Set;

/**
 * Класс команды, которая обновляет значение элемента коллекции с выбранным id
 */
public class Update implements Command{

    private final CollectionManager collectionManager;
    private final Console console;
    private final FlatReader flatReader;

    /**
     * @param collectionManager Хранит ссылку на созданный объект CollectionManager.
     * @param console            Хранит ссылку на объект класса Console.
     */
    public Update(CollectionManager collectionManager, Console console, FlatReader flatReader) {
        this.collectionManager = collectionManager;
        this.console = console;
        this.flatReader = flatReader;
    }

    /**
     * Метод, исполняющий команду. При вызове изменяется указанной элемент коллекции до тех пор,
     * пока в качестве аргумента не будет передан stop
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (args.isEmpty()) throw new WrongArgumentException();

        int id = Integer.parseInt(args);
        Set<Integer> keys = collectionManager.getKeys();
        int key = -1;
        for (int k : keys){
            if (collectionManager.getFlat(k).getId() == id) {
                key = k;
                break;
            }
        }
        try {
            if (collectionManager.containsKey(key)) {

                console.printCommandTextNext(getFieldName());

                String command;
                do {
                    console.printCommandText("Введите название поля для изменения, " +
                            "\"stop\", чтобы остановить изменения: ");
                    command = console.readLine().trim();
                    try {
                        update(key, command);
                    } catch (InvalidValueException ex) {
                        console.printCommandTextNext("Поле не распознано. Повторите ввод.");
                    }

                } while (!command.equals("stop"));
            } else console.printCommandError("Элемента с данным id не существует");
        } catch (IndexOutOfBoundsException ex) {
            console.printCommandError("Не указаны все аргументы команды");
        } catch (NumberFormatException ex) {
            console.printCommandError("Формат аргумента не соответствует" + ex.getMessage());
        }
    }

    //Метод, изменяющий поле выбранного элемента коллекции
    private void update(int key, String field) throws InvalidValueException {
        switch (field) {
            case "name": {
                collectionManager.getFlat(key).setName(flatReader.readName());
                console.printCommandTextNext("Значение поля было изменено");
                break;
            }
            case "coordinate_x": {
                collectionManager.getFlat(key).setCoordinateX(flatReader.readCoordinatesX());
                console.printCommandTextNext("Значение поля было изменено");
                break;
            }
            case "coordinate_y": {
                collectionManager.getFlat(key).setCoordinateY(flatReader.readCoordinatesY());
                console.printCommandTextNext("Значение поля было изменено");
                break;
            }
            case "area": {
                collectionManager.getFlat(key).setArea(flatReader.readArea());
                console.printCommandTextNext("Значение поля было изменено");
                break;
            }
            case "number_of_rooms": {
                collectionManager.getFlat(key).setNumberOfRooms(flatReader.readNumberOfRooms());
                console.printCommandTextNext("Значение поля было изменено");
                break;
            }
            case "number_of_bathrooms": {
                collectionManager.getFlat(key).setNumberOfBathrooms(flatReader.readNumberOfBathrooms());
                console.printCommandTextNext("Значение поля было изменено");
                break;
            }
            case "furnish": {
                collectionManager.getFlat(key).setFurnish(flatReader.readFurnish());
                console.printCommandTextNext("Значение поля было изменено");
                break;
            }
            case "view": {
                collectionManager.getFlat(key).setView(flatReader.readView());
                console.printCommandTextNext("Значение поля было изменено");
                break;
            }
            case "house_name": {
                collectionManager.getFlat(key).setHouseName(flatReader.readHouseName());
                console.printCommandTextNext("Значение поля было изменено");
                break;
            }
            case "house_year": {
                collectionManager.getFlat(key).setHouseYear(flatReader.readHouseYear());
                console.printCommandTextNext("Значение поля было изменено");
                break;
            }
            case "house_number_of_floors": {
                collectionManager.getFlat(key).setHouseNumberOfFloors(flatReader.readHouseNumberOfFloors());
                console.printCommandTextNext("Значение поля было изменено");
                break;
            }
            case "house_number_of_flats_on_floor": {
                collectionManager.getFlat(key).setHouseNumberOfFlatsOnFloor(flatReader.readHouseNumberOfFlatsOnFloor());
                console.printCommandTextNext("Значение поля было изменено");
                break;
            }
            case "house_number_of_lifts": {
                collectionManager.getFlat(key).setHouseNumberOfLifts(flatReader.readHouseNumberOfLifts());
                console.printCommandTextNext("Значение поля было изменено");
                break;
            }
            case "stop": {
                break;
            }
            default: {
                throw new InvalidValueException();
            }
        }
    }
    private String getFieldName() {
        return "Список всех полей:\nname\ncoordinate_x\ncoordinate_y\n" +
                "area\nnumber_of_rooms\nnumber_of_bathrooms\nfurnish: " + Arrays.toString(Furnish.values())
                + "\nview: " + Arrays.toString(View.values()) +
                "\nhouse_name\nhouse_year\nhouse_number_of_floors\nhouse_number_of_flats_on_floor\nhouse_number_of_lifts";
    }



    /**
     * @return Метод, возвращающий описание команды.
     * @see Command
     */
    @Override
    public String getDescription() {
        return "изменяет указанное поле выбранного id элемента коллекции";
    }
}
