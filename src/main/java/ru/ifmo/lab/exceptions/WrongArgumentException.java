package ru.ifmo.lab.exceptions;

/**
 * Исключение, выбрасываемое, когда аргумент команды не удовлетворяет требованиям
 * @author Kliodt Vadim
 * @version 1.0
 */
public class WrongArgumentException extends Exception{
    public WrongArgumentException(){}
    public WrongArgumentException(String message){
        super(message);
    }
}
