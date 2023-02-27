package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.collection.Flat;
import ru.ifmo.lab.exceptions.WrongArgumentException;
import ru.ifmo.lab.utility.Console;
import ru.ifmo.lab.utility.FlatReader;

public class Insert implements CommandWithArguments {
    private CollectionManager collectionManager;
    private Console console;
    private FlatReader flatReader;
    private String[] arguments;

    /**
     * Конструктор класса.
     *
     * @param collectionManager  Хранит ссылку на объект CollectionManager.
     * @param console            Хранит ссылку на объект класса Console.
     * @param flatReader Хранит ссылку на объект, осуществляющий чтение полей из указанного в console потока ввода.
     */
    public Insert(CollectionManager collectionManager, Console console, FlatReader flatReader) {
        this.collectionManager = collectionManager;
        this.console = console;
        this.flatReader = flatReader;
    }

    /**
     * Метод, исполняющий команду. При запуске команды запрашивает ввод указанных полей.
     * При успешном выполнении команды в потоке вывода высветится уведомление о добавлении элемента в коллекцию.
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (args.isEmpty()) throw new WrongArgumentException();
        try {
            if (!collectionManager.containsKey(Integer.parseInt(arguments[0]))) {
                console.printCommandText("Введите значения полей для элемента коллекции\n");
                Flat flat = flatReader.read(Integer.parseInt(arguments[0]));
                collectionManager.insert(Integer.parseInt(arguments[0]), flat);
                console.printCommandText("Элемент добавлен в коллекцию\n");
            } else System.err.println("Элемент с данным ключом уже существует в коллекции");
        } catch (IndexOutOfBoundsException ex) {
            System.err.println("Не указаны аргументы команды.");
        } catch (NumberFormatException ex) {
            System.err.println("Формат аргумента не соответствует целочисленному" + ex.getMessage());
        }
    }

    /**
     * @param arguments Аргументы команды.
     * @see CommandWithArguments
     */
    @Override
    public void getCommandArguments(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * @return Возвращает описание данной команды.
     * @see Command
     */
    @Override
    public String getDescription() {
        return "добавляет элемент с указанным ключом в качестве атрибута";
    }
}
