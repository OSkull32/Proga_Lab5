package ru.ifmo.lab.utility;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Console {
    Scanner scanner;

    public Console() {
        scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void printCommandText(String str) {
        System.out.println(str);
    }

    public void printCommandError(String str) {
        System.err.println(str);
    }

    public void printPreamble() {
        System.out.println(">");
    }
}
