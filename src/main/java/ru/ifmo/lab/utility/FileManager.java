package ru.ifmo.lab.utility;

import java.io.*;

/**
 * Класс, осуществляющий чтение/запись данных
 */
public class FileManager {
    private Console console;
    private File file;
    public FileManager(Console console){
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
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;

        StringBuffer stringBuffer = new StringBuffer();
        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
        } catch (IOException ex) {
            System.err.println("Произошла ошибка при добавлении файла во входящий поток " + ex);
            System.exit(-1);
        } catch (NullPointerException ex) {
            System.err.println("Не указан файл из которого нужно читать данные " + ex);
            System.exit(-1);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            } catch (IOException ex) {
                System.err.println("Возникла ошибка при закрытии файла " + ex);
            }
        }
        return stringBuffer.toString();
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
            fileWriter = new FileWriter(file);
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
