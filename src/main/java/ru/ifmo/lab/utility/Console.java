package ru.ifmo.lab.utility;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Console {

    // хранит ссылку на Scanner
    private final Scanner scanner;

    /**
     * Конструктор класса без параметров. При вызове scanner производит чтение из стандартного потока ввода с кодировкой UTF-8
     */
    public Console() {
        scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    /**
     * Метод, считывающий данные из места, на которое ссылается поле scanner
     *
     * @return возвращает считанную строку
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Метод, выводящий текст в стандартный поток вывода с переносом строки.
     *
     * @param str строка, которая выводиться в стандартный поток вывода
     */
    public void printCommandTextNext(String str) {
        System.out.println(str);
    }

    /**
     * Метод, выводящий текст в стандартный поток вывода без переноса строки.
     *
     * @param str строка, которая выводиться в стандартный поток вывода
     */
    public void printCommandText(String str) {
        System.out.print(str);
    }

    /**
     * Метод, выводящий текст ошибки в стандартный поток вывода ошибок
     *
     * @param str строка, которая выводиться в стандартный поток вывода ошибок
     */
    public void printCommandError(String str) {
        System.out.println("Ошибка: " + str);
    }

    /**
     * Метод, выводящий символ стрелки перед запросом ввода команды
     */
    public void printPreamble() {
        System.out.print(">");
    }
}
