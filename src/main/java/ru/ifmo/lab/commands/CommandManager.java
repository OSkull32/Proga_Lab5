package ru.ifmo.lab.commands;

import ru.ifmo.lab.exceptions.InvalidCommandException;
import ru.ifmo.lab.exceptions.WrongArgumentException;
import ru.ifmo.lab.utility.Console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Класс, управляющий вызовом команд.
 * @author Kliodt Vadim
 * @version 2.0
 */
public class CommandManager {

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
        String clearString = inputString.replaceAll("\s+", " ").trim(); //remove sequences of spaces

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
