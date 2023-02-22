package ru.ifmo.lab.collection;

public class Flat {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int area; //Значение поля должно быть больше 0
    private long numberOfRooms; //Максимальное значение поля: 14, Значение поля должно быть больше 0
    private long numberOfBathrooms; //Значение поля должно быть больше 0
    private Furnish furnish; //Поле не может быть null
    private View view; //Поле может быть null
    private House house; //Поле может быть null

    public void setName(String value) {
    }

    public void setCoordinateX(int i) {
    }

    public void setCoordinateY(int parseInt) {
    }
}
