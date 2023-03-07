package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.collection.View;
import ru.ifmo.lab.exceptions.WrongArgumentException;

/**
 * Класс команды, удаляющая элементы вид которых, соответствует заданному
 */
public class RemoveAllByView implements Command{
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса.
     *
     * @param collectionManager Хранит ссылку на объект CollectionManager.
     */
    public RemoveAllByView(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
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
            collectionManager.removeAllByView(View.valueOf(args));
        } catch (IllegalArgumentException ex) {
            System.err.println("Выбранной константы нет в перечислении.");
            System.out.println("Список всех констант:");
            for (View view : View.values()) {
                System.out.println(view);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Не указаны аргументы команды.");
        }
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
