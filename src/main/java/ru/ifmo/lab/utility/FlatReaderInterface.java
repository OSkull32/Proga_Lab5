package ru.ifmo.lab.utility;

import ru.ifmo.lab.collection.*;

public interface FlatReaderInterface {
    Flat read();
    String readName();
    Coordinates readCoordinates();
    int readArea();
    long readNumberOfRooms();
    long readNumberOfBathrooms();
    Furnish readFurnish();
    View readView();
    House readHouse();

}
