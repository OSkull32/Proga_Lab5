package ru.ifmo.lab.commands;

import ru.ifmo.lab.exceptions.InvalidCommandException;
import ru.ifmo.lab.exceptions.InvalidValueException;
import ru.ifmo.lab.utility.Console;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс, управляющий вызовом команд.
 * @author Kliodt Vadim
 * @version 1.0
 */
public class CommandManager {

    private final Console CONSOLE;
    private final HashMap<String, Command> COMMANDS = new HashMap<>();
    private final ArrayList<String> HISTORY_LIST = new ArrayList<>();

    /**
     * Конструирует менеджера команд с заданными {@link Console}
     * @param console Объект {@link Console}, через который класс
     *                осуществляет взаимодействие с пользователем.
     */
    public CommandManager(Console console) {
        this.CONSOLE = console;
    }

    /**
     * Добавляет команду к общему списку и делает ее возможной для вызова.
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

        String[] inputs = clearString.split(" ");

        try {
            executeCommand(inputs[0]);
        } catch (InvalidCommandException e) {
            CONSOLE.printCommandError("Invalid command");
        } catch (InvalidValueException e) {
            CONSOLE.printCommandError("Invalid value");
        }
    }

    //метод вызывает команду на исполнение
    private void executeCommand(String commandName) throws InvalidCommandException, InvalidValueException {

        Command command = COMMANDS.get(commandName);
        if (command == null) throw new InvalidCommandException();
        command.execute();

        HISTORY_LIST.add(commandName);

        if (HISTORY_LIST.size() > 13) {
            HISTORY_LIST.remove(0);
        }
    }

    /**
     * Печатает в консоль последние 13 использованных команд.
     */
    public void getHistoryList() {
        if (HISTORY_LIST.size() == 0) {
            CONSOLE.printCommandText("History is empty");
        } else {
            CONSOLE.printCommandText(HISTORY_LIST.toString());
        }
    }
}
