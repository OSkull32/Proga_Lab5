package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.InvalidValueException;
import ru.ifmo.lab.exceptions.WrongArgumentException;
import ru.ifmo.lab.utility.Console;

/**
 * Класс команды, которая обновляет значение элемента коллекции с выбранным id
 */
public class Update implements Command{

    private final CollectionManager collectionManager;

    private final Console console;

    /**
     * @param collectionManager Хранит ссылку на созданный объект CollectionManager.
     * @param console            Хранит ссылку на объект класса Console.
     */
    public Update(CollectionManager collectionManager, Console console) {
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Метод, исполняющий команду. При вызове изменяется указанной элемент коллекции до тех пор,
     * пока в качестве аргумента не будет передан stop
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (args.isEmpty()) throw new WrongArgumentException();

        int id = Integer.parseInt(args);
        int key = collectionManager.getKey(id);
        try {
            if (collectionManager.containsKey(key)) {

                console.printCommandTextNext(collectionManager.getFieldName());

                String command;
                do {
                    console.printCommandText("Введите название поля для изменения, " +
                            "\"stop\", чтобы остановить изменения: ");
                    command = console.readLine().trim();
                    try {
                        collectionManager.update(key, command);
                    } catch (InvalidValueException ex) {
                        console.printCommandTextNext("Поле не распознано. Повторите ввод.");
                    }

                } while (!command.equals("stop"));
            } else console.printCommandError("Элемента с данным id не существует");
        } catch (IndexOutOfBoundsException ex) {
            console.printCommandError("Не указаны все аргументы команды");
        } catch (NumberFormatException ex) {
            console.printCommandError("Формат аргумента не соответствует" + ex.getMessage());
        }
    }


    /**
     * @return Метод, возвращающий описание команды.
     * @see Command
     */
    @Override
    public String getDescription() {
        return "изменяет указанное поле выбранного id элемента коллекции";
    }
}
