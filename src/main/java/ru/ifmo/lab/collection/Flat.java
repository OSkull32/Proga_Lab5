package ru.ifmo.lab.collection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс объектов коллекции
 */
public class Flat {
    /*
     * Идентификатор коллекции. Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
     */
    private int id;
    /*
     * Имя объекта класса. Поле не может быть null, Строка не может быть пустой
     */
    private String name;
    /*
     * Координаты объекта класса. Поле не может быть null
     */
    private Coordinates coordinates;
    /*
     * Время создания объекта класса. Поле не может быть null, Значение этого поля должно генерироваться автоматически
     */
    private LocalDateTime creationDate;
    /*
     * Площадь объекта класса. Значение поля должно быть больше 0
     */
    private int area;
    /*
     * Количество комнат объекта класса. Максимальное значение поля: 14, Значение поля должно быть больше 0
     */
    private long numberOfRooms;
    /*
     * Количество уборных объекта класса. Значение поля должно быть больше 0
     */
    private long numberOfBathrooms;
    /*
     * Отделка объекта класса. Поле не может быть null
     */
    private Furnish furnish;
    /*
     * Вид из объекта класса. Поле может быть null
     */
    private View view;
    /*
     * Дом объекта класса. Поле может быть null
     */
    private House house;

    /**
     * Конструктор объекта класса
     *
     * @param id Идентификатор объекта коллекции
     * @param name Имя объекта класса
     * @param coordinates Координаты объекта класса
     * @param localDateTime Время создания объекта класса
     * @param area Площадь объекта класса
     * @param numberOfRooms Количество комнат объекта класса
     * @param numberOfBathrooms Количество уборных объекта класса
     * @param furnish Отделка объекта класса
     * @param view Вид из объекта класса
     * @param house Дом объекта класса
     */
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

    /**
     * Метод, возвращающий идентификатор объекта класса
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Метод, возвращающий имя объекта класса
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, возвращающий координаты объекта класса
     *
     * @return coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Метод, возвращающий время создания объекта класса
     *
     * @return creationDate
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Метод, возвращающий отформатированное время создания объекта класса
     *
     * @return formattedCreationDate
     */
    public String getFormattedCreationDate() {
        String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(pattern);
        return creationDate.plusHours(3).format(europeanDateFormatter);
    }

    /**
     * Метод, возвращающий площадь объекта класса
     *
     * @return area
     */
    public int getArea() {
        return area;
    }

    /**
     * Метод, возвращающий число комнат объекта класса
     *
     * @return numberOfRooms
     */
    public long getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Метод, возвращающий число уборных объекта класса
     *
     * @return numberOfBathrooms
     */
    public long getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    /**
     * Метод, возвращающий отделку объекта класса
     *
     * @return furnish
     */
    public Furnish getFurnish() {
        return furnish;
    }

    /**
     * Метод, возвращающий вид из объекта класса
     *
     * @return view
     */
    public View getView() {
        return view;
    }

    /**
     * Метод, возвращающий дом объекта класса
     *
     * @return house
     */
    public House getHouse() {
        return house;
    }

    /**
     * Метод, присваивающий имя объекта класса
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод, присваивающий координату x объекта класса
     *
     * @param x
     */
    public void setCoordinateX(int x) {
        this.getCoordinates().setX(x);
    }

    /**
     * Метод, присваивающий координату y объекта класса
     *
     * @param y
     */
    public void setCoordinateY(Integer y) {
        this.getCoordinates().setY(y);
    }

    /**
     * Метод, присваивающий площадь объекта класса
     *
     * @param area
     */
    public void setArea(int area) {
        this.area = area;
    }

    /**
     * Метод, присваивающий количество комнат объекта класса
     *
     * @param numberOfRooms
     */
    public void setNumberOfRooms(long numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Метод, присваивающий количество уборных объекта класса
     *
     * @param numberOfBathrooms
     */
    public void setNumberOfBathrooms(long numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    /**
     * Метод, присваивающий отделку объекта класса
     *
     * @param furnish
     */
    public void setFurnish(Furnish furnish) {
        this.furnish = furnish;
    }

    /**
     * Метод, присваивающий вид из объекта класса
     *
     * @param view
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * Метод, присваивающий дом объекта класса
     *
     * @param house
     */
    public void setHouse(House house) {
        this.house = house;
    }

    /**
     * Метод, возвращающий отформатированный вывод полей объекта класса
     *
     * @return поля объекта класса
     */
    @Override
    public String toString() {
        return "Flat{" +
                "\nid=" + id +
                ", \nname='" + name + '\'' +
                ", \ncoordinates=" + coordinates +
                ", \ncreationDate=" + creationDate +
                ", \narea=" + area +
                ", \nnumberOfRooms=" + numberOfRooms +
                ", \nnumberOfBathrooms=" + numberOfBathrooms +
                ", \nfurnish=" + furnish +
                ", \nview=" + view +
                ", \nhouse=" + house +
                '}';
    }
}
