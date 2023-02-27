package ru.ifmo.lab.collection;

import ru.ifmo.lab.utility.Console;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Map;

/**
 * Класс отвечающий за работу с коллекциями
 */
public class CollectionManager {
    /*
     * Коллекция, с которой осуществляется работа
     */
    private final Hashtable<Integer, Flat> hashtable;

    private final Console console;
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
     * Нужно дорабоать
     *
     * @param id
     * @param field
     * @param value
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
     * Метод, удаляющий все элементы коллекции
     */
    public void clear() {
        hashtable.clear();
    }


}
