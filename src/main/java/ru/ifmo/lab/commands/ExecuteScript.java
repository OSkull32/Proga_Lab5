package ru.ifmo.lab.commands;

import ru.ifmo.lab.exceptions.RecursiveException;
import ru.ifmo.lab.exceptions.WrongArgumentException;
import ru.ifmo.lab.utility.Console;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс команды, которая считывает и исполняет скрипт из указанного файла
 */
public class ExecuteScript implements Command {

    private final CommandManager commandManager;

    private String scriptPath;

    private final Script script = new Script();
    private final Console CONSOLE;

    /**
     * Конструктор класса
     *
     * @param commandManager Хранит ссылку на объект CommandManager
     */
    public ExecuteScript(CommandManager commandManager, Console console) {
        this.commandManager = commandManager;
        this.CONSOLE = console;
    }


    //Статический класс, в котором храниться коллекция адресов скрипта
    private static class Script {
        private final ArrayList<String> scriptPaths = new ArrayList<>();

        //Метод, добавляющий скрипт в коллекцию.
        private void putScript(String scriptPath) {
            scriptPaths.add(scriptPath);
        }
        // Метод, убирающий скрипт в коллекцию.
        private void removeScript(String scriptPath) {
            scriptPaths.remove(scriptPath);
        }
    }

    /**
     * Метод исполняющий команду.
     *
     * @param args Строка, содержащая переданные команде аргументы.
     */
    @Override
    public void execute(String args) throws WrongArgumentException {
        try {
            if (!args.isEmpty()) {
                scriptPath = args;
                if (script.scriptPaths.contains(scriptPath)) throw new RecursiveException();
                else script.putScript(scriptPath);
            } else throw new IllegalArgumentException();
            File file = new File(scriptPath);
            if (!file.canRead() || file.isDirectory() || !file.isFile())  throw new IOException();

            FileInputStream fileInputStream = new FileInputStream(scriptPath);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            Scanner scanner = new Scanner(bufferedInputStream);

            while (scanner.hasNext()) {
                commandManager.executeScript(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            CONSOLE.printCommandError("Файл скрипта не найден");
        } catch (NullPointerException ex) {
            CONSOLE.printCommandError("Не выбран файл из которого читать скрипт");
        } catch (IOException ex) {
            CONSOLE.printCommandError("Доступ к файлу невозможен " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            CONSOLE.printCommandError("Скрипт не передан в качестве аргумента команды");
        }catch (RecursiveException ex) {
            CONSOLE.printCommandError("Скрипт " + scriptPath + " уже существует, произошел рекурсивный вызов");
        }
        script.removeScript(scriptPath);
    }

    /**
     * @see Command
     * @return Описание команды execute_script
     */
    @Override
    public String  getDescription() {
        return "выполняет команды, описанные в скрипте";
    }
}
