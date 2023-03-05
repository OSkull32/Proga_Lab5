package ru.ifmo.lab.utility;

import com.google.gson.Gson;
import ru.ifmo.lab.collection.Flat;

import java.util.Hashtable;

public class JsonParser {

    private Gson gson = new Gson();
    public String encode(Flat hashtable){
        String json = gson.toJson(hashtable);
        return json;

    };

//    public Flat decode(String jsonString){};
}
