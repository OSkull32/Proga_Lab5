package ru.ifmo.lab.utility;

import ru.ifmo.lab.collection.*;
import ru.ifmo.lab.exceptions.InvalidValueException;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Класс, необходимы для чтения полей объекта Flat
 */
public class FlatReader {
    /*
     * Поле, хранящее ссылку на объект класса типа Console
     */
    private final Console console;

    /**
     * Конструктор класса, который присваивает console значение, переданное в конструкторе в качестве параметра
     *
     * @param console хранит ссылку на объект типа Console
     */
    public FlatReader(Console console) {
        this.console = console;
    }

    /**
     * Метод, выполняющий чтение данных из консоли. Вввод полей в поределенном порядке
     *
     * @param id Идентификатор объекта класса Flat, который записывается в качестве ключа в коллекцию
     * @return объект типа Flat
     */
    public Flat read(int id){
        String i = Instant.now().toString();
        return new Flat(id, readName(), readCoordinates(), LocalDateTime.parse(i), readArea(), readNumberOfRooms(), readNumberOfBathrooms(), readFurnish(), readView(), readHouse());
    }

    /**
     * Метод, который читает поле name объекта Flat из потока, указанного в поле console.
     *
     * @return значение поля name, уже проверенное на условия допустимости
     */
    public String readName() {
        //String str;

        while (true) {
            console.printCommandText("name (not null): ");
            String str = console.readLine().trim();
            if (str.equals("")) console.printCommandError("\n Значение поля не может быть null или пустой строкой \n");
            else return str;
        }
    }

    /**
     * Метод, который читает координаты x и y
     *
     * @return объект типа Coordinates
     */
    public Coordinates readCoordinates() {
        return new Coordinates(readCoordinatesX(), readCoordinatesY());
    }

    /**
     * Метод, который читает поле x объекта Flat из потока, указанного в поле console.
     *
     * @return значение поля x, уже проверенное на условия допустимости
     */
    public int readCoordinatesX() {
        int x;

        while (true) {
            try {
                console.printCommandText("coordinate x(int & x <= 713): ");
                String str = console.readLine().trim();
                if (str.equals("")) x = 0;
                else {
                    x = Integer.parseInt(str);
                    if (x > 713) throw new InvalidValueException();
                }
                return x;
            } catch (InvalidValueException ex) {
                System.out.println("Координата x должна быть не более 713");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа int");
            }
        }
    }

    /**
     * Метод, который читает поле y объекта Flat из потока, указанного в поле console.
     *
     * @return значение поля y, уже проверенное на условия допустимости
     */
    public Integer readCoordinatesY() {
        Integer y;
        while (true) {
            try {
                console.printCommandText("coordinate y(integer & not null & y > -397): ");
                y = Integer.parseInt(console.readLine().trim());
                if (y <= -397) throw new InvalidValueException();
                else return y;
            } catch (InvalidValueException ex) {
                System.out.println("Координата y должна быть больше -397");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа Integer и не null");
            }
        }
    }

    /**
     * Метод, который читает поле area объекта Flat из потока, указанного в поле console.
     *
     * @return значение поля area, уже проверенное на условия допустимости
     */
    public int readArea() {
        int area;
        while (true) {
            try {
                console.printCommandText("area(int & area > 0): ");
                String str = console.readLine().trim();
                if (str.equals("")) area = Integer.parseInt(null);
                else {
                    area = Integer.parseInt(str);
                    if (area <= 0) throw new InvalidValueException();
                }
                return area;
            } catch (InvalidValueException ex) {
                System.out.println("Значение area должно быть больше 0");
            }   catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа int");
            }
        }
    }

    /**
     * Метод, который читает поле numberOfRooms объекта Flat из потока, указанного в поле console.
     *
     * @return значение поля numberOfRooms, уже проверенное на условия допустимости
     */
    public long readNumberOfRooms() {
        long numberOfRooms;
        while (true) {
            try {
                console.printCommandText("numberOfRooms(int & 0 < numberOfRooms <=14): ");
                String str = console.readLine().trim();
                if (str.equals("")) numberOfRooms = Long.parseLong(null);
                else {
                    numberOfRooms = Long.parseLong(str);
                    if (numberOfRooms <= 0 || numberOfRooms > 14) throw new InvalidValueException();
                }
                return numberOfRooms;
            } catch (InvalidValueException ex) {
                System.out.println("Значение numberOfRooms должно быть больше 0 и не более 14");
            }   catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа long");
            }
        }
    }

    /**
     * Метод, который читает поле numberOfBathrooms объекта Flat из потока, указанного в поле console.
     *
     * @return значение поля numberOfBathrooms, уже проверенное на условия допустимости
     */
    public long readNumberOfBathrooms() {
        long numberOfBathrooms;
        while (true) {
            try {
                console.printCommandText("numberOfBathrooms(int & numberOfBathrooms > 0): ");
                String str = console.readLine().trim();
                if (str.equals("")) numberOfBathrooms = Long.parseLong(null);
                else {
                    numberOfBathrooms = Long.parseLong(str);
                    if (numberOfBathrooms <= 0) throw new InvalidValueException();
                }
                return numberOfBathrooms;
            } catch (InvalidValueException ex) {
                System.out.println("Значение numberOfBathrooms должно быть больше 0");
            }   catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа long");
            }
        }
    }

    /**
     * Метод, который читает поле furnish объекта Flat из потока, указанного в поле console.
     * Выводит список допустимых констант
     *
     * @return значение поля furnish, уже проверенное на условия допустимости
     */
    public Furnish readFurnish() {
        Furnish furnish;
        while (true) {
            try {
                console.printCommandText("Допустимые значения furnish:\n");
                for (Furnish val : Furnish.values()) {
                    console.printCommandText(val.name() + "\n");
                }
                console.printCommandText("furnish: ");
                furnish = Furnish.valueOf(console.readLine().toUpperCase().trim());
                return furnish;
            }   catch (IllegalArgumentException ex) {
                System.err.println("Введенная константа не представлена в допустимых значения Furnish");
            }
        }
    }

    /**
     * Метод, который читает поле view объекта Flat из потока, указанного в поле console.
     * Выводит список допустимых констант
     *
     * @return значение поля view, уже проверенное на условия допустимости
     */
    public View readView() {
        View view;
        while (true) {
            try {
                console.printCommandText("Допустимые значения view:\n");
                for (View val : View.values()) {
                    console.printCommandText(val.name() + "\n");
                }
                console.printCommandText("view: ");
                view = View.valueOf(console.readLine().toUpperCase().trim());
                return view;
            }   catch (IllegalArgumentException ex) {
                System.err.println("Введенная константа не представлена в допустимых значения View");
            }
        }
    }

    /**
     * Метод, который читает поля House.
     *
     * @return объект типа House
     */
    public House readHouse() {
        return new House(readHouseName(), readHouseYear(), readHouseNumberOfFloors(), readHouseNumberOfFlatsOnFloor(), readHouseNumberOfLifts());
    }

    /**
     * Метод, который читает поле name объекта Flat из потока, указанного в поле console.
     *
     * @return значение поля area, уже проверенное на условия допустимости
     */
    public String readHouseName() {
        String str;

        while (true) {
            console.printCommandText("House name (not null): ");
            str = console.readLine().trim();
            if (str == null) console.printCommandError("\n Значение поля не может быть null\n");
            else return str;
        }
    }

    /**
     * Метод, который читает поле year объекта Flat из потока, указанного в поле console.
     *
     * @return значение поля year, уже проверенное на условия допустимости
     */
    public int readHouseYear() {
        int houseYear;
        while (true) {
            try {
                console.printCommandText("houseYear(int & houseYear > 0): ");
                String str = console.readLine().trim();
                if (str.equals("")) houseYear = Integer.parseInt(null);
                else {
                    houseYear = Integer.parseInt(str);
                    if (houseYear <= 0) throw new InvalidValueException();
                }
                return houseYear;
            } catch (InvalidValueException ex) {
                System.out.println("Значение houseYear должно быть больше 0");
            }   catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа int");
            }
        }
    }

    /**
     * Метод, который читает поле numberOfFloors объекта Flat из потока, указанного в поле console.
     *
     * @return значение поля numberOfFloors, уже проверенное на условия допустимости
     */
    public Long readHouseNumberOfFloors() {
        Long numberOfFloors;
        while (true) {
            try {
                console.printCommandText("number of floors(long & 0 < numberOfFloors <= 39): ");
                String str = console.readLine().trim();
                if (str.equals("")) numberOfFloors = null;
                else {
                    numberOfFloors = Long.parseLong(str);
                    if (numberOfFloors <= 0 || numberOfFloors > 39) throw new InvalidValueException();
                }
                return numberOfFloors;
            } catch (InvalidValueException ex) {
                System.out.println("Значение numberOfFloors должно быть больше 0 и не более 39");
            }   catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа Long");
            }
        }
    }

    /**
     * Метод, который читает поле numberOfFlatsOnFloor объекта Flat из потока, указанного в поле console.
     *
     * @return значение поля numberOfFlatsOnFloor, уже проверенное на условия допустимости
     */
    public long readHouseNumberOfFlatsOnFloor() {
        Long numberOfFlatsOnFloor;
        while (true) {
            try {
                console.printCommandText("number of flats on floor(long & numberOfFlatsOnFloor > 0): ");
                String str = console.readLine().trim();
                if (str.equals("")) numberOfFlatsOnFloor = null;
                else {
                    numberOfFlatsOnFloor = Long.parseLong(str);
                    if (numberOfFlatsOnFloor <= 0) throw new InvalidValueException();
                }
                return numberOfFlatsOnFloor;
            } catch (InvalidValueException ex) {
                System.out.println("Значение numberOfFlatsOnFloor должно быть больше 0");
            }   catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа long");
            }
        }
    }

    /**
     * Метод, который читает поле numberOfLifts объекта Flat из потока, указанного в поле console.
     *
     * @return значение поля numberOfLifts, уже проверенное на условия допустимости
     */
    public long readHouseNumberOfLifts() {
        Long numberOfLifts;
        while (true) {
            try {
                console.printCommandText("number of lifts(long & numberOfLifts > 0): ");
                String str = console.readLine().trim();
                if (str.equals("")) numberOfLifts = null;
                else {
                    numberOfLifts = Long.parseLong(str);
                    if (numberOfLifts <= 0) throw new InvalidValueException();
                }
                return numberOfLifts;
            } catch (InvalidValueException ex) {
                System.out.println("Значение numberOfLifts должно быть больше 0");
            }   catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа long");
            }
        }
    }
}
