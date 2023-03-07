package ru.ifmo.lab.commands;

import ru.ifmo.lab.collection.CollectionManager;
import ru.ifmo.lab.exceptions.InvalidCommandException;
import ru.ifmo.lab.exceptions.WrongArgumentException;
import ru.ifmo.lab.utility.Console;
import ru.ifmo.lab.utility.FlatReader;

import java.util.*;

/**
 * Класс, управляющий вызовом команд.
 *
 * @author Kliodt Vadim
 * @version 2.0
 */
public class CommandManager {
    private CollectionManager collectionManager;
    private FlatReader flatReader;
    ExecuteScript.Script script;
    private String inputFile;
    private final Console CONSOLE;
    private final HashMap<String, Command> COMMANDS = new HashMap<>();
    private final ArrayList<String> HISTORY_LIST = new ArrayList<>();
    private final int MAX_HISTORY_SIZE = 13;
    Console console = new Console();
    CommandManager commandManager = new CommandManager(console);

    /**
     * Конструирует менеджера команд с заданными {@link Console}
     *
     * @param console Объект {@link Console}, через который класс
     *                осуществляет взаимодействие с пользователем.
     */
    public CommandManager(Console console) {
        this.CONSOLE = console;
    }

    /**
     * Конструктор класса
     *
     * @param collectionManager Хранит ссылку на объект CollectionManager
     * @param console Хранит ссылку на объект класса Console
     * @param flatReader Хранит ссылку на объект, осуществляющий чтение полей из console
     * @param inputFile Хранит строку, в которой записан адрес файла
     */
    public CommandManager(CollectionManager collectionManager, Console console, String inputFile, FlatReader flatReader) {
        this.collectionManager = collectionManager;
        this.CONSOLE = console;
        this.flatReader = flatReader;
        this.inputFile = inputFile;
        this.script = new ExecuteScript.Script();
        this.putCommands();
    }

    /**
     * Конструктор класса
     *
     * @param collectionManager Хранит ссылку на объект CollectionManager
     * @param console Хранит ссылку на объект класса Console
     * @param flatReader Хранит ссылку на объект, осуществляющий чтение полей из console
     * @param script Хранит объект класса, из которого мы получаем список адресов скрипта
     */
    public CommandManager(CollectionManager collectionManager, Console console, FlatReader flatReader, ExecuteScript.Script script) {
        this.collectionManager = collectionManager;
        this.CONSOLE = console;
        this.flatReader = flatReader;
        this.script = script;
        this.putCommands();
    }

    /**
     * Метод, добавляющий команды в соответствующие коллекции
     */
    private void putCommands() {
        COMMANDS.put("clear", new Clear(collectionManager));
        COMMANDS.put("execute_script", new ExecuteScript(collectionManager, flatReader, script));
        COMMANDS.put("exit", new Exit());
        COMMANDS.put("filter_less_than_house", new FilterLessThanHouse(collectionManager));
        COMMANDS.put("help", new Help(commandManager));
        COMMANDS.put("history", new History(commandManager));
        COMMANDS.put("info", new Info(collectionManager));
        COMMANDS.put("update", new Update(collectionManager, console));
        COMMANDS.put("insert", new Insert(collectionManager, console, flatReader));
        COMMANDS.put("print_field_ascending_house", new PrintFieldAscendingHouse(collectionManager));
        COMMANDS.put("remove_all_by_view", new RemoveAllByView(collectionManager));
        COMMANDS.put("remove_greater_key", new RemoveGreaterKey(collectionManager));
        COMMANDS.put("remove_key", new RemoveKey(collectionManager));
        COMMANDS.put("remove_lower_key", new RemoveLowerKey(collectionManager));
        COMMANDS.put("save", new Save(collectionManager));
        COMMANDS.put("show", new Show(collectionManager));
    }

    /**
     * Метод, который определяет из полученной строки команду и исполняет ее
     *
     * @param firstCommand Первая строка команды
     */
    public void execute(String firstCommand) throws WrongArgumentException {
        String[] words = firstCommand.trim().split("\\s+");
        String[] args = Arrays.copyOfRange(words, 1, words.length);

        if (COMMANDS.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            Command command;

            command = COMMANDS.get(words[0].toLowerCase(Locale.ROOT));
            command.execute(Arrays.toString(args));
        } else {
            System.err.println("Команда " + words[0] + " не распознана, используйте help для справки.");
        }
    }
    /**
     * Добавляет команду к общему списку и делает ее возможной для вызова.
     *
     * @param name название команды.
     * @param command объект класса команды.
     */
    public void addCommand(String name, Command command) {
        COMMANDS.put(name, command);
    }

    /**
     * При вызове этого метода в консоли запрашивается команда.
     */
    public void nextCommand() {
        CONSOLE.printPreamble(); //print ">>>"
        String inputString = CONSOLE.readLine();
        String clearString = inputString.replaceAll("\\s+", " ").trim(); //remove sequences of spaces

        String[] inputs = clearString.split(" ", 2);

        try {
            executeCommand(inputs);
        } catch (InvalidCommandException e) {
            CONSOLE.printCommandError("Invalid command");
        } catch (WrongArgumentException e) {
            CONSOLE.printCommandError(e.getMessage());
        }
    }

    //метод вызывает команду на исполнение
    private void executeCommand(String[] inputs) throws InvalidCommandException, WrongArgumentException {

        Command command = COMMANDS.get(inputs[0]);
        if (command == null) throw new InvalidCommandException();
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
            CONSOLE.printCommandText("History is empty");
        } else {
            CONSOLE.printCommandText("History (latest " + MAX_HISTORY_SIZE + " commands): " +
                    HISTORY_LIST.toString().replace("[", "").replace("]", ""));
        }
    }

    /**
     * Печатает в консоль описание по всем командам.
     * @see Command#getDescription()
     */
    public void getCommandsInfo(){ //команда help
        Set<String> commandNames = COMMANDS.keySet();
        for (String commandName : commandNames){
            CONSOLE.printCommandText(commandName + ": " + COMMANDS.get(commandName).getDescription());
        }
    }
}
