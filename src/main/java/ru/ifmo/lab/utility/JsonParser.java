package ru.ifmo.lab.utility;

import com.google.gson.Gson;
import ru.ifmo.lab.collection.Flat;

import java.util.Hashtable;

public class JsonParser {
    private static final Gson GSON = new Gson();
    public static String encode(Hashtable<Integer, Flat> hashtable){
        return GSON.toJson(hashtable);
    }

//    public Flat decode(String jsonString){};
}
