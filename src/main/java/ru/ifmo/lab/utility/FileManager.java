package ru.ifmo.lab.utility;

import java.io.*;

/**
 * Класс, осуществляющий чтение/запись данных
 *
 * @author Владимир Данченко, Kloodt Vadim
 * @version 2.0
 */
public class FileManager {
    private Console console;
    private File file;

    public FileManager(Console console) {
        this.console = console;
        this.file = new File("C:\\Users\\79215\\Desktop\\test.json");
        //TODO сделать взятие файла из консоли
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
        } catch (IOException | SecurityException e) {
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
