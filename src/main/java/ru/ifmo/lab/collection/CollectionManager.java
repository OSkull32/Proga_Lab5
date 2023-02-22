package ru.ifmo.lab.collection;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CollectionManager {
    Hashtable<Integer, Flat> hashtable;

    ZonedDateTime collectionInitialization;

    public CollectionManager() {
        this.hashtable = new Hashtable<>();
        String i = Instant.now().toString();
        collectionInitialization = ZonedDateTime.parse(i);
    }

    public void info() {
        System.out.println("Коллекция " + hashtable.getClass().getSimpleName());
        System.out.println("Тип элементов коллекции: " + Flat.class.getSimpleName());
        String pattern = "yyyy-mm-dd HH:mm:ss.SSS";
        DateTimeFormatter europeanDateFormat = DateTimeFormatter.ofPattern(pattern);
        System.out.println("Время инициализации коллекции: " + collectionInitialization.plusHours(3).format(europeanDateFormat));
        System.out.println("Количество элементов в коллекции: " + hashtable.size());
    }

    public void show() {
        if (hashtable.size() == 0) {
            System.out.println("Коллекция пуста.");
        } else {
            for (Map.Entry<Integer, Flat> entry : hashtable.entrySet()) {
                System.out.println(entry.getValue().toString());
            }
        }
    }

    public void insert(Integer id, Flat flat) {
        if (hashtable.get(id) == null) {
            hashtable.put(id, flat);
        } else System.out.println("Элемент с данным ключом уже существует");
    }

    public void update(Integer id, String field, String value) {
        try {
            switch (field) {
                case "name": {
                    if (value.equals("")) throw new NullPointerException();
                    hashtable.get(id).setName(value);
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "coordinate_x": {
                    if (value.equals("")) value = null;
                    hashtable.get(id).setCoordinateX(Integer.parseInt(value));
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "coordinate_y": {
                    if (value.equals("")) value = null;
                    hashtable.get(id).setCoordinateY(Integer.parseInt(value));
                    System.out.println("Значение поля было изменено");
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

    public void clear(){
        hashtable.clear();
    }


}
