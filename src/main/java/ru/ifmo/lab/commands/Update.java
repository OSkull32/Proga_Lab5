package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
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
        try {
            if (collectionManager.containsKey(Integer.parseInt(args))) {
                console.printCommandTextNext(collectionManager.getFieldName());

                console.printCommandTextNext("Если хотите остановите изменение элемента, напишите stop");
                console.printCommandText("Введите название поля и желаемое значение для изменения:");
                String[] commandWords = new String[0];
                do {
                    try {
                        commandWords = console.readLine().trim().split("\\s+");
                        if (commandWords.length == 1) {
                            collectionManager.update(Integer.parseInt(args), commandWords[0], "");
                        } else
                            collectionManager.update(Integer.parseInt(args), commandWords[0], commandWords[1]);
                    } catch (IndexOutOfBoundsException ex) {
                        console.printCommandError("Не введено поле");
                    }
                } while (!commandWords[0].equals("stop"));
            } else console.printCommandError("Элемента с данным ключом не существует");
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
