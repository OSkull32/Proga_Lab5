package ru.ifmo.lab.collection;

import ru.ifmo.lab.exceptions.InvalidValueException;
import ru.ifmo.lab.utility.Console;
import ru.ifmo.lab.utility.JsonParser;
import ru.ifmo.lab.utility.SortByHouse;
import ru.ifmo.lab.utility.FileManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс отвечающий за работу с коллекциями
 */
public class CollectionManager {

    // Коллекция, с которой осуществляется работа
    private final Hashtable<Integer, Flat> hashtable;

    private static final HashSet<Integer> allId = new HashSet<>();
    private final Console console;
    private final FileManager fileManager;
    // Время инициализации коллекции
    private final LocalDateTime collectionInitialization;

    /**
     * Конструктор, создающий новый объект менеджера коллекции
     */
    public CollectionManager(Console console, FileManager fileManager, Hashtable<Integer, Flat> hashtable) {
        if (hashtable != null) this.hashtable = hashtable;
        else this.hashtable = new Hashtable<>();
        this.console = console;
        this.fileManager = fileManager;

        String i = LocalDateTime.now().toString();
        collectionInitialization = LocalDateTime.parse(i);
    }

    /**
     * Метод, выводящий основную информацию об коллекции
     */
    public void info() {
        console.printCommandTextNext("Коллекция: " + hashtable.getClass().getSimpleName());
        console.printCommandTextNext("Тип элементов коллекции: " + Flat.class.getSimpleName());
        String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        DateTimeFormatter europeanDateFormat = DateTimeFormatter.ofPattern(pattern);
        console.printCommandTextNext("Время инициализации коллекции: " + collectionInitialization.format(europeanDateFormat));
        console.printCommandTextNext("Количество элементов в коллекции: " + hashtable.size());
    }

    /**
     * Метод, выводящий элементы коллекции
     */
    public void show() {
        if (hashtable.size() == 0) {
            console.printCommandTextNext("Коллекция пуста.");
        } else {
            for (Map.Entry<Integer, Flat> entry : hashtable.entrySet()) {
                console.printCommandTextNext(entry.getValue().toString());
            }
        }
    }

    /**
     * Метод, добавляющий новый элемент в коллекцию
     *
     * @param key   идентификатор элемента
     * @param flat элемент коллекции, который нужно добавить
     */
    public void insert(Integer key, Flat flat) {
        if (hashtable.get(key) == null) {
            hashtable.put(key, flat);
        } else console.printCommandTextNext("Элемент с данным ключом уже существует");
    }

    /**
     * Метод, изменяющий поле выбранного элемента коллекции
     *
     * @param id идентификатор
     * @param field имя поля
     * @param value значение поля
     */
    public void update(Integer id, String field, String value) {
        Flat flat = null;
        for (Flat flat1 : hashtable.values()){
            if (flat1.getId() == id) {
                flat = flat1;
                break;
            }
        }

        if (flat == null) return;
        try {
            switch (field) {
                case "name": {
                    if (value.equals("")) throw new InvalidValueException();
                    flat.setName(value);
                    console.printCommandTextNext("Значение поля было изменено");
                    break;
                }
                case "coordinate_x": {
                    if (value.equals("")) throw new InvalidValueException();
                    flat.setCoordinateX(Integer.parseInt(value));
                    console.printCommandTextNext("Значение поля было изменено");
                    break;
                }
                case "coordinate_y": {
                    if (value.equals("")) throw new InvalidValueException();
                    flat.setCoordinateY(Integer.parseInt(value));
                    console.printCommandTextNext("Значение поля было изменено");
                    break;
                }
                case "area": {
                    if (value.equals("")) throw new InvalidValueException();
                    flat.setArea(Integer.parseInt(value));
                    console.printCommandTextNext("Значение поля было изменено");
                    break;
                }
                case "number_of_rooms": {
                    if (value.equals("")) throw new InvalidValueException();
                    flat.setNumberOfRooms(Long.parseLong(value));
                    console.printCommandTextNext("Значение поля было изменено");
                    break;
                }
                case "number_of_bathrooms": {
                    if (value.equals("")) throw new InvalidValueException();
                    flat.setNumberOfBathrooms(Long.parseLong(value));
                    console.printCommandTextNext("Значение поля было изменено");
                    break;
                }
                case "furnish": {
                    if (value.equals("")) throw new InvalidValueException();
                    flat.setFurnish(Furnish.valueOf(value.toUpperCase(Locale.ROOT)));
                    console.printCommandTextNext("Значение поля было изменено");
                    break;
                }
                case "view": {
                    if (value.equals("")) throw new InvalidValueException();
                    flat.setView(View.valueOf(value.toUpperCase(Locale.ROOT)));
                    console.printCommandTextNext("Значение поля было изменено");
                    break;
                }
                case "house_name": {
                    if (value.equals("")) throw new NullPointerException();
                    flat.setHouseName(value);
                    console.printCommandTextNext("Значение поля было изменено");
                    break;
                }
                case "house_year": {
                    if (value.equals("")) throw new InvalidValueException();
                    hashtable.get(key).setHouseYear(Integer.parseInt(value));
                    console.printCommandTextNext("Значение поля было изменено");
                    break;
                }
                case "house_number_of_floors": {
                    if (value.equals("")) throw new InvalidValueException();
                    hashtable.get(key).setHouseNumberOfFloors(Long.parseLong(value));
                    console.printCommandTextNext("Значение поля было изменено");
                    break;
                }
                case "house_number_of_flats_on_floor": {
                    if (value.equals("")) throw new InvalidValueException();
                    hashtable.get(key).setHouseNumberOfFlatsOnFloor(Long.parseLong(value));
                    console.printCommandTextNext("Значение поля было изменено");
                    break;
                }
                case "house_number_of_lifts": {
                    if (value.equals("")) throw new InvalidValueException();
                    hashtable.get(key).setHouseNumberOfLifts(Long.parseLong(value));
                    console.printCommandTextNext("Значение поля было изменено");
                    break;
                }
                case "stop": {
                    break;
                }
                default: {
                    console.printCommandTextNext("Поле не распознано");
                    break;
                }
            }
        } catch (ClassCastException ex) {
            console.printCommandError("Указано недопустимое значение для данного поля");
        } catch (IllegalArgumentException ex) {
            console.printCommandError("Значение аргумента не соответствует ожидаемому " + ex.getMessage());
        } catch (InvalidValueException ex) {
            console.printCommandError("Невозможно записать null в данное поле");
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
     * Метод, сохраняющий элементы коллекции в файл в формате JSON.
     */
    public void save() {
        fileManager.writeToFile(JsonParser.encode(hashtable));
    }

    /**
     * Метод, удаляющий все элементы коллекции, вид которого соответствует заданному
     *
     * @param view выбранный вид элемента коллекции
     */
    public void removeAllByView(View view) {
        int size = hashtable.size();
        ArrayList<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, Flat> entry : hashtable.entrySet()) {
            if (entry.getValue().getView().equals(view)) keys.add(entry.getKey());
        }
        for (Integer key : keys) {
            hashtable.remove(key);
        }
        if (size == hashtable.size()) console.printCommandTextNext("Не было найдено элементов с таким значением поля");
        else console.printCommandTextNext("Элементы с данным значением поля удалены");
    }

    /**
     * Метод, выводящий истину, если в коллекции существует элемент с выбранным ключом, иначе ложь
     *
     * @param key идентификатор элемента (ключ)
     * @return true - в коллекции существует элемент с выбранным ключом, false - такого элемента не существует
     */
    public boolean containsKey(int key) {
        return hashtable.containsKey(key);
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

    /**
     * Метод выводит в консоль список квартир, отсортированный относительно количества этажей в доме.
     */
    public void printFieldAscendingHouse() {
        Collection<Flat> flatCollection = hashtable.values();
        ArrayList<Flat> flatList = new ArrayList<>(flatCollection);
        flatList.sort(new SortByHouse());
        for (Flat flat : flatList) {
            console.printCommandTextNext("Квартира: " + flat.getName() + " в доме " + flat.getHouse());
        }
    }

    /**
     * Метод выводит в консоль список квартир в домах с меньшим числом этажей, чем указано в параметре.
     * @param numberOfFloors количество этажей.
     */
    public void filterLessThanHouse(int numberOfFloors) {
        Collection<Flat> flatCollection = hashtable.values();
        for (Flat flat : flatCollection){
            if (flat.getHouse().getNumberOfFloors() < numberOfFloors){
                console.printCommandText(flat.getName() + "; ");
            }
        }
    }

    public static int generateId(){
        int id;
        do {
            id = (int) (100000 * Math.random() + 1);
        }while (allId.contains(id));
        allId.add(id);
        return id;
    }
}
