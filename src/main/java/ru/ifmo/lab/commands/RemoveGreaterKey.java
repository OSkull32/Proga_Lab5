package ru.ifmo.lab.commands;

import ru.ifmo.lab.exceptions.WrongArgumentException;

public class RemoveGreaterKey implements Command{
    @Override
    public void execute(String args) throws WrongArgumentException {
        if (args.isEmpty()) throw new WrongArgumentException();
        //code here
    }
}
