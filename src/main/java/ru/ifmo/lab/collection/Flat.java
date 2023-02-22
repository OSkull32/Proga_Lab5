package ru.ifmo.lab.collection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flat {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int area; //Значение поля должно быть больше 0
    private long numberOfRooms; //Максимальное значение поля: 14, Значение поля должно быть больше 0
    private long numberOfBathrooms; //Значение поля должно быть больше 0
    private Furnish furnish; //Поле не может быть null
    private View view; //Поле может быть null
    private House house; //Поле может быть null

    public Flat(int id, String name, Coordinates coordinates, LocalDateTime localDateTime, int area, long numberOfRooms, long numberOfBathrooms, Furnish furnish, View view, House house) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = localDateTime;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.furnish = furnish;
        this.view = view;
        this.house = house;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getFormattedCreationDate() {
        String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(pattern);
        return creationDate.plusHours(3).format(europeanDateFormatter);
    }

    public int getArea() {
        return area;
    }

    public long getNumberOfRooms() {
        return numberOfRooms;
    }

    public long getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public Furnish getFurnish() {
        return furnish;
    }

    public View getView() {
        return view;
    }

    public House getHouse() {
        return house;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinateX(int x) {
        this.getCoordinates().setX(x);
    }

    public void setCoordinateY(Integer y) {
        this.getCoordinates().setY(y);
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setNumberOfRooms(long numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setNumberOfBathrooms(long numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public void setFurnish(Furnish furnish) {
        this.furnish = furnish;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Flat id = " + this.getId() + "\n\tname: " + this.getName() + "\n\tcoordinates:"
                + this.getCoordinates().toString() + "\n\tcreation date: " + this.getFormattedCreationDate()
                + "\n\tarea: " + this.getArea() + "\n\tnumber of rooms " + this.getNumberOfRooms()
                + "\n\tnumber of bathrooms " + this.getNumberOfBathrooms() + "\n\tfurnish "
                + this.getFurnish().toString() + "\n\tview " + this.getView().toString() + "\n\thouse "
                + this.getHouse().toString();
    }
}
