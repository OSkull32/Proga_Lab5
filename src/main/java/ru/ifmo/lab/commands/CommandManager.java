package ru.ifmo.lab.commands;

import ru.ifmo.lab.utility.Console;

import java.util.HashMap;

public class CommandManager {

    private Console console;
    private HashMap<String, Command> commands = new HashMap<>();

    public CommandManager(Console console,
                          Clear clear,
                          ExecuteScript executeScript,
                          Exit exit,
                          FilterLessThanHouse filterLessThanHouse,
                          Help help,
                          History history,
                          Info info,
                          InsertId insertId,
                          InsertNull insertNull,
                          PrintFieldAscendingHouse printFieldAscendingHouse,
                          RemoveAllByView removeAllByView,
                          RemoveGreaterKey removeGreaterKey,
                          RemoveKey removeKey,
                          RemoveLowerKey removeLowerKey,
                          Save save,
                          Show show) {
        this.console = console;
        commands.put("clear", clear);
        commands.put("execute_script", executeScript);
        commands.put("exit", exit);
        commands.put("filter_less_than_house", filterLessThanHouse);
        commands.put("help", help);
        commands.put("history", history);
        commands.put("info", info);
        commands.put("update", insertId);
        commands.put("insert", insertNull);
        commands.put("print_field_ascending_house", printFieldAscendingHouse);
        commands.put("remove_all_by_view", removeAllByView);
        commands.put("remove_greater_key", removeGreaterKey);
        commands.put("remove_key", removeKey);
        commands.put("remove_lower_key", removeLowerKey);
        commands.put("save", save);
        commands.put("show", show);
    }

    public void nextCommand() {
        console.printPreamble();
        executeCommand("history"); //TODO взятие, проверка и фильтрация аргумента для executeCommand
    }

    private void executeCommand(String commandName) {
        Command command = commands.get(commandName);
        command.execute();
    }
}
