package ru.ifmo.lab.collection;

import ru.ifmo.lab.utility.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс отвечающий за работу с коллекциями
 */
public class CollectionManager {

    // Коллекция, с которой осуществляется работа
    private final Hashtable<Integer, Flat> hashtable;
    private final HashSet<Integer> allId = new HashSet<>();
    private final Console console;
    private final LocalDateTime collectionInitialization; // Время инициализации коллекции
    /**
     * Максимальный ID у объектов коллекции
     */
    public static final int MAX_ID = 100000;

    /**
     * Конструктор, создающий новый объект менеджера коллекции
     */
    public CollectionManager(Console console, Hashtable<Integer, Flat> hashtable) {
        if (hashtable != null) this.hashtable = hashtable;
        else this.hashtable = new Hashtable<>();
        this.console = console;

        String i = LocalDateTime.now().toString();
        collectionInitialization = LocalDateTime.parse(i);

        for (Flat flat : this.hashtable.values()){
            allId.add(flat.getId());
        }
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
     * Метод возвращает квартиру по ее ключу
     * @param key ключ
     * @return квартира
     */
    public Flat getFlat(int key){
        return hashtable.get(key);
    }

    /**
     * Метод, добавляющий новый элемент в коллекцию
     *
     * @param key   идентификатор элемента
     * @param flat элемент коллекции, который нужно добавить
     */
    public void insert(Integer key, Flat flat) {
        if (!hashtable.contains(key)) {
            hashtable.put(key, flat);
            allId.add(flat.getId());
        } else {
            console.printCommandTextNext("Элемент с данным ключом уже существует");
        }
    }

    /**
     * Метод, удаляющий выбранный по идентификатору элемент коллекции
     *
     * @param key идентификатор элемента коллекции (ключ)
     */
    public void removeKey(Integer key) {
        Flat flat = hashtable.remove(key);
        allId.remove(flat.getId());
    }

    /**
     * Возвращает все ключи
     * @return Множество ключей
     */
    public Set<Integer> getKeys(){
        return hashtable.keySet();
    }

    /**
     * Получить всю коллекцию
     * @return коллекция
     */
    public Hashtable<Integer, Flat> getCollection(){
        return hashtable;
    }

    /**
     * Метод, удаляющий все элементы коллекции
     */
    public void clear() {
        allId.clear();
        hashtable.clear();
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

    public boolean containsId (int id){
        return allId.contains(id);
    }
}
