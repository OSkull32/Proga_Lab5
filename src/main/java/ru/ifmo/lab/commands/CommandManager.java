package ru.ifmo.lab.commands;

import ru.ifmo.lab.exceptions.InvalidCommandException;
import ru.ifmo.lab.exceptions.InvalidValueException;
import ru.ifmo.lab.utility.Console;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandManager {

    private final Console CONSOLE;
    private HashMap<String, Command> commands = new HashMap<>();
    private ArrayList<String> historyList = new ArrayList<>();
    private final int MAX_HISTORY_SIZE = 13;

    public CommandManager(Console console){
        this.CONSOLE = console;
    }

    public void addCommand(String name, Command command){
        commands.put(name, command);
    }

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

    private void executeCommand(String commandName) throws InvalidCommandException, InvalidValueException {

        Command command = commands.get(commandName);
        if (command == null) throw new InvalidCommandException();
        command.execute();

        historyList.add(commandName);
        if (historyList.size() > MAX_HISTORY_SIZE){
            historyList.remove(0);
        }
    }

    public void getHistoryList() {
        if (historyList.size() == 0) {
            CONSOLE.printCommandText("History is empty");
        } else{
            CONSOLE.printCommandText(historyList.toString());
        }
    }
}
