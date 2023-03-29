package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.ErrorInScriptException;
import ru.ifmo.lab.exceptions.InvalidCommandException;
import ru.ifmo.lab.exceptions.WrongArgumentException;
import ru.ifmo.lab.utility.Console;
import ru.ifmo.lab.utility.FlatReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Класс, управляющий вызовом команд.
 *
 * @author Kliodt Vadim
 * @version 2.0
 */
public class CommandManager {
    private final Console CONSOLE;
    private final HashMap<String, Command> COMMANDS = new HashMap<>();
    private final ArrayList<String> HISTORY_LIST = new ArrayList<>();
    private final CollectionManager COLLECTION_MANAGER;
    private final FlatReader FLAT_READER;
    private final int MAX_HISTORY_SIZE = 13;

    /**
     * Конструирует менеджера команд с заданными {@link Console}
     *
     * @param console Объект {@link Console}, через который класс
     *                осуществляет взаимодействие с пользователем.
     */
    public CommandManager(Console console, CollectionManager collectionManager, FlatReader flatReader) {
        this.CONSOLE = console;
        this.COLLECTION_MANAGER = collectionManager;
        this.FLAT_READER = flatReader;
        putAllCommands();
    }

    /**
     * Метод, исполняющий скрипт.
     */
    public void executeCommandFromScript() {
        nextCommand();
    }

    // Добавляет команду к общему списку и делает ее возможной для вызова.
    private void addCommand(String name, Command command) {
        COMMANDS.put(name, command);
    }

    // метод добавляет все команды в список
    private void putAllCommands() {
        addCommand("clear", new Clear(COLLECTION_MANAGER, CONSOLE));
        addCommand("execute_script", new ExecuteScript(this, CONSOLE));
        addCommand("exit", new Exit(CONSOLE));
        addCommand("filter_less_than_house", new FilterLessThanHouse(COLLECTION_MANAGER, CONSOLE));
        addCommand("help", new Help(this));
        addCommand("history", new History(this));
        addCommand("info", new Info(COLLECTION_MANAGER));
        addCommand("update", new Update(COLLECTION_MANAGER, CONSOLE));
        addCommand("insert", new Insert(COLLECTION_MANAGER, CONSOLE, FLAT_READER));
        addCommand("print_field_ascending_house", new PrintFieldAscendingHouse(COLLECTION_MANAGER));
        addCommand("remove_all_by_view", new RemoveAllByView(COLLECTION_MANAGER, CONSOLE));
        addCommand("remove_greater_key", new RemoveGreaterKey(COLLECTION_MANAGER, CONSOLE));
        addCommand("remove_key", new RemoveKey(COLLECTION_MANAGER, CONSOLE));
        addCommand("remove_lower_key", new RemoveLowerKey(COLLECTION_MANAGER, CONSOLE));
        addCommand("save", new Save(COLLECTION_MANAGER, CONSOLE));
        addCommand("show", new Show(COLLECTION_MANAGER));
    }

    /**
     * При вызове этого метода в консоли запрашивается команда.
     */
    public void nextCommand() {
        CONSOLE.printPreamble(); //print ">"
        String[] inputs = CONSOLE
                .readLine()
                .trim()
                .split("\\s+", 2);

        try {
            executeCommand(inputs);
        } catch (InvalidCommandException | WrongArgumentException e) {
            CONSOLE.printCommandError(e.getMessage());
        }
    }

    //метод вызывает команду на исполнение
    private void executeCommand(String[] inputs) throws InvalidCommandException, WrongArgumentException {

        if (inputs[0].equals("")) return;
        Command command = COMMANDS.get(inputs[0]);

        if (command == null) throw new InvalidCommandException("введена несуществующая команда");
        if (inputs.length == 1) {
            command.execute(""); //если не было передано аргументов
        } else {
            command.execute(inputs[1]); //если было передано 1 и более аргументов
        }

        HISTORY_LIST.add(inputs[0]);

        if (HISTORY_LIST.size() > MAX_HISTORY_SIZE) {
            HISTORY_LIST.remove(0);
        }
    }

    /**
     * Печатает в консоль последние 13 использованных команд.
     */
    public void getHistoryList() { //команда history
        if (HISTORY_LIST.size() == 0) {
            CONSOLE.printCommandTextNext("History is empty");
        } else {
            CONSOLE.printCommandTextNext("History (latest " + MAX_HISTORY_SIZE + " commands): " +
                    HISTORY_LIST.toString().replace("[", "").replace("]", ""));
        }
    }

    /**
     * Печатает в консоль описание по всем командам.
     *
     * @see Command#getDescription()
     */
    public void getCommandsInfo() { //команда help
        Set<String> commandNames = COMMANDS.keySet();
        for (String commandName : commandNames) {
            CONSOLE.printCommandTextNext(commandName + ": " + COMMANDS.get(commandName).getDescription());
        }
    }
}