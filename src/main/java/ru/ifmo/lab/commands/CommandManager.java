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
    private final Console CONSOLE;
    private final HashMap<String, Command> COMMANDS = new HashMap<>();
    private final ArrayList<String> HISTORY_LIST = new ArrayList<>();
    private final int MAX_HISTORY_SIZE = 13;

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
     * Метод, который определяет из полученной строки команду и исполняет ее
     *
     * @param firstCommand Первая строка команды
     */
    public void executeScript(String firstCommand) throws WrongArgumentException {
        String[] words = firstCommand.trim().split("\\s+");
        String[] args = Arrays.copyOfRange(words, 1, words.length);

        if (COMMANDS.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            Command command;

            command = COMMANDS.get(words[0].toLowerCase(Locale.ROOT));
            command.execute(Arrays.toString(args).replace("[", "").replace("]", ""));
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
            CONSOLE.printCommandTextNext("History is empty");
        } else {
            CONSOLE.printCommandTextNext("History (latest " + MAX_HISTORY_SIZE + " commands): " +
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
            CONSOLE.printCommandTextNext(commandName + ": " + COMMANDS.get(commandName).getDescription());
        }
    }
}
