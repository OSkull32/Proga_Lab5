package ru.ifmo.lab.commands;

public class Exit implements Command{
    public Exit(){
    }

    @Override
    public void execute() {
        System.out.println("Завершение работы программы");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Команда завершает программу";
    }
}
