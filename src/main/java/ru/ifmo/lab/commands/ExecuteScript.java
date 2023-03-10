package ru.ifmo.lab.commands;

import ru.ifmo.lab.exceptions.RecursiveException;
import ru.ifmo.lab.exceptions.WrongArgumentException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс команды, которая считывает и исполняет скрипт из указанного файла
 */
public class ExecuteScript implements Command {

    private final CommandManager commandManager;

    private String scriptPath;

    private final Script script;

    /**
     * Конструктор класса
     *
     * @param collectionManager Хранит ссылку на объект CollectionManager
     * @param flatReader Хранит ссылку на объект, осуществляющий чтение полей из console
     * @param script Хранит объект класса, из которого мы получаем список адресов скрипта
     */
    public ExecuteScript(Script script, CommandManager commandManager) {
        this.commandManager = commandManager;
        this.script = script;
    }

    /**
     * Статический класс, в котором храниться коллекция адресов скрипта
     */
    public static class Script {
        private final ArrayList<String> scriptPaths = new ArrayList<>();

        /**
         * Метод, добавляющий скрипт в коллекцию.
         *
         * @param scriptPath Адрес скрипта
         */
        public void putScript(String scriptPath) {
            scriptPaths.add(scriptPath);
        }

        /**
         * Метод, убирающий скрипт в коллекцию.
         *
         * @param scriptPath Адрес скрипта
         */
        public void removeScript(String scriptPath) {
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
            System.err.println("Файл скрипта не найден");
        } catch (NullPointerException ex) {
            System.err.println("Не выбран файл из которого читать скрипт");
        } catch (IOException ex) {
            System.err.println("Доступ к файлу невозможен " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.err.println("Скрипт не передан в качестве аргумента команды");
        }catch (RecursiveException ex) {
            System.err.println("Скрипт " + scriptPath + " уже существует, произошел рекурсивный вызов");
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
