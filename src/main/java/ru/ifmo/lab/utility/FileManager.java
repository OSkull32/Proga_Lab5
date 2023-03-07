package ru.ifmo.lab.utility;

import java.io.*;

/**
 * Класс, осуществляющий чтение/запись данных
 *
 * @author Владимир Данченко, Kliodt Vadim
 * @version 2.0
 */
public class FileManager {
    private final Console console;
    private File file;

    public FileManager(Console console) {
        this.console = console;
        do {
            try {
                this.file = askForFile();
            } catch (IOException e) {
                console.printCommandError("ошибка при создании. Повторите попытку.");
            }
        } while (this.file == null);
        console.printCommandText("Файл успешно добавлен");
    }

    //метод запрашивает у пользователя имя файла и проверяет его на валидность
    private File askForFile() throws IOException {
        console.printCommandText("Введите имя файла (абсолютный путь или путь относительно " +
                "директории проекта): ");
        String path = console.readLine();
        File file = new File(path);
        if (file.isFile()) {
            return file; //если все ОК
        }
        if (file.isDirectory()) { //если указана директория
            console.printCommandError("Указана директория. Повторите попытку.");
            return null;
        }
        if (!file.exists()) { //если файла вообще не существует
            console.printCommandError("файл не существует. Введите \"create\", чтобы создать.");
            if (console.readLine().equals("create")) {
                if (file.createNewFile()) {
                    console.printCommandText("Файл создан");
                    return file;
                } else {
                    console.printCommandError("файл с таким именем уже есть. Повторите попытку.");
                }
            } else {
                console.printCommandError("неверный ввод. Повторите попытку.");
            }
        }
        return null;
    }

    /**
     * Метод, который читает данные из файла.
     *
     * @return строка, которая хранит все содержимое данного файла
     */
    public String readFromFile() {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            byte[] bytes = bufferedInputStream.readAllBytes();
            return new String(bytes);
        } catch (FileNotFoundException e) {
            // TODO добавить общий метод для случаев FileNotFoundException-ов
        } catch (IOException e) {
            console.printCommandError("невозможно прочитать файл. " + e.getMessage());
        }
        return null;
    }

    /**
     * Метод, который записывает данные в файл
     *
     * @param str строка, которую нужно записать в файл
     */
    public void writeToFile(String str) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(str);

        } catch (IOException ex) {
            System.err.println("Произошла ошибка при добавлении файла в поток " + ex);
        } catch (NullPointerException ex) {
            System.err.println("Не указан файл, в который нужно записать данные " + ex);
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException ex) {
                System.err.println("Произошла ошибка при закрытии " + ex);
            }
        }
    }
}
