package ru.ifmo.lab.utility;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.ifmo.lab.collection.Flat;

import java.lang.reflect.Type;
import java.util.Hashtable;

/**
 * Класс позволяет работать с файлами формата Json.
 *
 * @author Kliodt Vadim
 * @version 1.0
 */
public class JsonParser {
    private static final Gson GSON = new Gson();
    // следующая строка была взята из документации на библиотеку GSON
    private static final Type HASHTABLE_TYPE = new TypeToken<Hashtable<Integer, Flat>>() {}.getType();

    /**
     * Метод представляет коллекцию в формате Json для последующего сохранения в файл
     *
     * @param hashtable список ({@link Hashtable}) с объектим типа {@link Flat}
     * @return строку в Json формате
     */
    public static String encode(Hashtable<Integer, Flat> hashtable) {
        return GSON.toJson(hashtable);
    }

    /**
     * Переводит коллекцию, переданную ему в Json формате,
     * в виде списка ({@link Hashtable}) объектов типа {@link Flat}
     *
     * @param jsonString строка в Json формате
     * @return список ({@link Hashtable}) с объектим типа {@link Flat}
     */
    public static Hashtable<Integer, Flat> decode(String jsonString) {
        return GSON.fromJson(jsonString, HASHTABLE_TYPE);
    }
}
