package ru.ifmo.lab.collection;

import ru.ifmo.lab.utility.Console;
import ru.ifmo.lab.utility.FileManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс отвечающий за работу с коллекциями
 */
public class CollectionManager {
    /*
     * Коллекция, с которой осуществляется работа
     */
    private final Hashtable<Integer, Flat> hashtable;

    Console console;
    /*
     * Время инициализации коллекции
     */
    private final LocalDateTime collectionInitialization;

    /**
     * Конструктор, создающий новый объект менеджера коллекции
     */
    public CollectionManager(Console console) {
        this.hashtable = new Hashtable<>();
        String i = LocalDateTime.now().toString();
        collectionInitialization = LocalDateTime.parse(i);
        this.console = console;
    }

    /**
     * Метод, выводящий основную информацию об коллекции
     */
    public void info() {
        console.printCommandText ("Коллекция: " + hashtable.getClass().getSimpleName());
        console.printCommandText("Тип элементов коллекции: " + Flat.class.getSimpleName());
        String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        DateTimeFormatter europeanDateFormat = DateTimeFormatter.ofPattern(pattern);
        console.printCommandText("Время инициализации коллекции: " + collectionInitialization.format(europeanDateFormat));
        console.printCommandText("Количество элементов в коллекции: " + hashtable.size());
    }

    /**
     * Метод, выводящий элементы коллекции
     */
    public void show() {
        if (hashtable.size() == 0) {
            console.printCommandText("Коллекция пуста.");
        } else {
            for (Map.Entry<Integer, Flat> entry : hashtable.entrySet()) {
                console.printCommandText(entry.getValue().toString());
            }
        }
    }

    /**
     * Метод, добавляющий новый элемент в коллекцию
     *
     * @param id   идентификатор элемента
     * @param flat элемент коллекции, который нужно добавить
     */
    public void insert(Integer id, Flat flat) {
        if (hashtable.get(id) == null) {
            hashtable.put(id, flat);
        } else console.printCommandText("Элемент с данным ключом уже существует");
    }

    /**
     * Метод, изменяющий поле выбранного элемента коллекции
     *
     * @param id идентификатор (ключ)
     * @param field имя поля
     * @param value значение поля
     */
    public void update(Integer id, String field, String value) {
        try {
            switch (field) {
                case "name": {
                    if (value.equals("")) throw new NullPointerException();
                    hashtable.get(id).setName(value);
                    console.printCommandText("Значение поля было изменено");
                    break;
                }
                case "coordinate_x": {
                    if (value.equals("")) value = null;
                    hashtable.get(id).setCoordinateX(Integer.parseInt(value));
                    console.printCommandText("Значение поля было изменено");
                    break;
                }
                case "coordinate_y": {
                    if (value.equals("")) value = null;
                    hashtable.get(id).setCoordinateY(Integer.parseInt(value));
                    console.printCommandText("Значение поля было изменено");
                    break;
                }
                case "area": {
                    if (value.equals("")) value = null;
                    hashtable.get(id).setArea(Integer.parseInt(value));
                    console.printCommandText("Значение поля было изменено");
                    break;
                }
                case "number_of_rooms": {
                    if (value.equals("")) value = null;
                    hashtable.get(id).setNumberOfRooms(Long.parseLong(value));
                    console.printCommandText("Значение поля было изменено");
                    break;
                }
                case "number_of_bathrooms": {
                    if (value.equals("")) value = null;
                    hashtable.get(id).setNumberOfBathrooms(Long.parseLong(value));
                    console.printCommandText("Значение поля было изменено");
                    break;
                }
                case "furnish": {
                    hashtable.get(id).setFurnish(Furnish.valueOf(value.toUpperCase(Locale.ROOT)));
                    console.printCommandText("Значение поля было изменено");
                    break;
                }
                case "view": {
                    hashtable.get(id).setView(View.valueOf(value.toUpperCase(Locale.ROOT)));
                    console.printCommandText("Значение поля было изменено");
                    break;
                }
                case "house_name": {
                    if (value.equals("")) throw new NullPointerException();
                    hashtable.get(id).setHouseName(value);
                    console.printCommandText("Значение поля было изменено");
                    break;
                }
                case "house_year": {
                    if (value.equals("")) value = null;
                    hashtable.get(id).setHouseYear(Integer.parseInt(value));
                    console.printCommandText("Значение поля было изменено");
                    break;
                }
                case "house_number_of_floors": {
                    if (value.equals("")) value = null;
                    hashtable.get(id).setHouseNumberOfFloors(Long.parseLong(value));
                    console.printCommandText("Значение поля было изменено");
                    break;
                }
                case "house_number_of_flats_on_floor": {
                    if (value.equals("")) value = null;
                    hashtable.get(id).setHouseNumberOfFlatsOnFloor(Long.parseLong(value));
                    console.printCommandText("Значение поля было изменено");
                    break;
                }
                case "house_number_of_lifts": {
                    if (value.equals("")) value = null;
                    hashtable.get(id).setHouseNumberOfLifts(Long.parseLong(value));
                    console.printCommandText("Значение поля было изменено");
                    break;
                }
                case "stop": {
                    break;
                }
                default: {
                    System.out.println("Поле не распознано");
                    break;
                }
            }
        } catch (ClassCastException ex) {
            System.err.println("Указано недопустимое значение для данного поля");
        } catch (IllegalArgumentException ex) {
            System.err.println("Значение аргумента не соответствует ожидаемому " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.err.println("Невозможно записать null в данное поле");
        }
    }

    /**
     * Метод, удаляющий выбранный по идентификатору элемент коллекции
     *
     * @param id идентификатор элемента коллекции (ключ)
     */
    public void removeKey(Integer id) {
        hashtable.remove(id);
    }

    /**
     * Метод, удаляющий все элементы коллекции, значение ключа которых меньше указанного
     *
     * @param id значение ключа, меньше которого следует удалять элементы
     */
    public void removeLowerKey(Integer id) {
        ArrayList<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, Flat> entry : hashtable.entrySet()) {
            if (entry.getKey() < id) keys.add(entry.getKey());
        }
        for (Integer key : keys) {
            hashtable.remove(key);
        }
    }

    public void removeGreaterKey(Integer id) {
        ArrayList<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, Flat> entry : hashtable.entrySet()) {
            if (entry.getKey() > id) keys.add(entry.getKey());
        }
        for (Integer key : keys) {
            hashtable.remove(key);
        }
    }

    /**
     * Метод, удаляющий все элементы коллекции
     */
    public void clear() {
        hashtable.clear();
    }

    /**
     * Метод, удаляющий все элементы коллекции, вид которого соответствует заданному
     *
     * @param view выбранный вид элемента коллекции
     */

    /**
     * Метод, сохраняющий элементы коллекции
     * Еще нужно написать парсер в JSON
     * @param filePath путь до файла, куда следует сохранить элементы коллекции
     */
    public void save(String filePath) {
//        Parser parser = new Parser();
        FileManager fileManager = new FileManager();

        Flat[] flats = new Flat[hashtable.size()];
        flats = hashtable.values().toArray(flats);
//        String str = parser.parseToJson(flats);
        fileManager.writeToFile(Arrays.toString(flats), filePath);
    }

    public void removeAllByView(View view) {
        int size = hashtable.size();
        ArrayList<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, Flat> entry : hashtable.entrySet()) {
            if (entry.getValue().getView().equals(view)) keys.add(entry.getKey());
        }
        for (Integer key : keys) {
            hashtable.remove(key);
        }
        if (size == hashtable.size()) System.out.println("Не было найдено элементов с таким значением поля");
        else System.out.println("Элементы с данным значением поля удалены");
    }

    /**
     * Метод, выводящий истину, если в коллекции существует элемент с выбранным ключом, иначе ложь
     *
     * @param id идентификатор элемента (ключ)
     * @return true - в коллекции существует элемент с выбранным ключом, false - такого элемента не существует
     */
    public boolean containsKey(int id) {
        return hashtable.containsKey(id);
    }

    /**
     * Метод, возвращающий названия всех полей коллекции, которые могут быть изменены
     *
     * @return Все поля коллекции выводит в столбец
     */
    public String getFieldName() {
        return "Список всех полей:\nname\ncoordinate_x\ncoordinate_y\n" +
                "area\nnumber_of_rooms\nnumber_of_bathrooms\nfurnish: " + Arrays.toString(Furnish.values())
                + "\nview: " + Arrays.toString(View.values()) +
                "\nhouse_name\nhouse_year\nhouse_number_of_floors\nhouse_number_of_flats_on_floor\nhouse_number_of_lifts";

    }
}
