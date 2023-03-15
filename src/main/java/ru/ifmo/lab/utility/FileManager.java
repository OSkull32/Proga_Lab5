package ru.ifmo.lab.utility;

import java.io.*;

/**
 * Класс, осуществляющий работу с файлом.
 *
 * @author Владимир Данченко, Kliodt Vadim
 * @version 2.0
 */
public class FileManager {
    private final Console console;
    private File file;

    public FileManager(Console console) {
        this.console = console;
    }

    /**
     * Метод добавляет файл для дальнейшей работы с ним.
     */
    public void addFile() {
        do {
            try {
                this.file = askForFile();
            } catch (IOException e) {
                console.printCommandError("ошибка при создании. Повторите попытку.");
            }
        } while (this.file == null);
        console.printCommandTextNext("Файл успешно добавлен");
    }

    //метод запрашивает у пользователя имя файла и проверяет его на валидность
    private File askForFile() throws IOException {
        console.printCommandText("Введите имя файла (абсолютный путь или путь относительно " +
                "директории проекта): ");
        String path = console.readLine();
        File file = new File(path);
        if (file.isFile() && file.canRead()) {
            return file; //если все ОК
        }
        if (file.isDirectory()) { //если указана директория
            console.printCommandError("указана директория. Повторите попытку.");
            return null;
        }
        if (!file.exists()) { //если файла вообще не существует
            console.printCommandError("файл не существует. Введите \"create\", чтобы создать.");
            if (console.readLine().equals("create")) {
                if (file.createNewFile()) {
                    console.printCommandTextNext("Файл создан");
                    return file;
                } else {
                    console.printCommandError("файл с таким именем уже есть. Повторите попытку.");
                }
            } else {
                console.printCommandError("неверный ввод. Повторите попытку.");
            }
        }
        if (!file.canRead()) {
            console.printCommandError("невозможно прочитать файл");
            return null;
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
            console.printCommandError("файл не найден. Повторите процесс выбора файла.");
            addFile();
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
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(str);
            bufferedWriter.flush();
        } catch (IOException ex) {
            console.printCommandError("Произошла ошибка при добавлении файла в поток " + ex);
        } catch (NullPointerException ex) {
            console.printCommandError("Не указан файл, в который нужно записать данные " + ex);
        }
    }
}
