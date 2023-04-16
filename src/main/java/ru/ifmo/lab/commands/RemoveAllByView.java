package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.collection.Flat;
import ru.ifmo.lab.collection.View;
import ru.ifmo.lab.exceptions.WrongArgumentException;
import ru.ifmo.lab.utility.Console;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Класс команды, удаляющая элементы вид которых, соответствует заданному
 */
public class RemoveAllByView implements Command{
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Конструктор класса.
     *
     * @param collectionManager Хранит ссылку на объект CollectionManager.
     */
    public RemoveAllByView(CollectionManager collectionManager, Console console) {
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Метод, удаляющий элементы коллекции с соответствующим полем view
     *
     * @param args Строка, содержащая переданные команде аргументы.
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (args.isEmpty()) throw new WrongArgumentException();
        try {
            if (args.equals("null")) {
                removeByView(null);
            }
            else removeByView(View.valueOf(args));
        } catch (IllegalArgumentException ex) {
            console.printCommandError("Выбранной константы нет в перечислении.");
            console.printCommandTextNext("Список всех констант:");
            for (View view : View.values()) {
                console.printCommandTextNext(view.toString());
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            console.printCommandError("Не указаны аргументы команды.");
        }
    }

    private void removeByView(View view){
        Hashtable<Integer, Flat> hashtable= collectionManager.getCollection();
        int size = hashtable.size();
        ArrayList<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, Flat> entry : hashtable.entrySet()) {
            if (entry.getValue().getView() == null) keys.add(entry.getKey());
            else if (entry.getValue().getView().equals(view)) keys.add((entry.getKey()));
        }
        for (Integer key : keys) {
            hashtable.remove(key);
        }
        if (size == hashtable.size()) console.printCommandTextNext("Не было найдено элементов с таким значением поля");
        else console.printCommandTextNext("Элементы с данным значением поля удалены");
    }

    /**
     * @return описание команды.
     *
     * @see Command
     */
    @Override
    public String getDescription() {
        return "удаляет элементы коллекции, поля View которого соответствуют введенному";
    }
}
