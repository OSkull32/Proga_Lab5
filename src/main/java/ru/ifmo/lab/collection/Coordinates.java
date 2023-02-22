package ru.ifmo.lab.collection;

import ru.ifmo.lab.utility.FieldValidatble;

public class Coordinates implements FieldValidatble {
    private int x; //Максимальное значение поля: 713
    private Integer y; //Значение поля должно быть больше -397, Поле не может быть null

    public Coordinates(int x, Integer y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(Integer y){
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public boolean validate() {
        if (x > 713) return false;
        return y > -397 && y != null;
    }

    @Override
    public String toString() {
        return "Coordinates (x,y) = (" + getX() + ", " + getY() + ")";
    }
}
