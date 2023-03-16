package ru.ifmo.lab.collection;

import ru.ifmo.lab.utility.Console;
import ru.ifmo.lab.utility.FlatReader;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class CollectionChecker {
    private final FlatReader FLAT_READER;
    private final Console CONSOLE;

    public CollectionChecker(FlatReader flatReader, Console console) {
        this.FLAT_READER = flatReader;
        this.CONSOLE = console;
    }

    public void checkCollection(Hashtable<Integer, Flat> collection) {
        if (collection == null) return;
        Set<Integer> keySet = Set.copyOf(collection.keySet());

        for (Integer key : keySet) {
            Flat flat = collection.get(key);

            //проверка полей
            if (!FlatChecker.checkId(flat.getId())) {
                collection.remove(key);
                CONSOLE.printCommandError("ошибка в поле ID у объекта: " + flat.getName() +
                        ". Объект удален из коллекции.");
                continue;
            }
            if (!FlatChecker.checkName(flat.getName())) {
                CONSOLE.printCommandError("ошибка в поле Name у объекта: " + flat.getName() +
                        ". Повторите ввод.");
                flat.setName(FLAT_READER.readName());
            }
            if (!FlatChecker.checkCoordinates(flat.getCoordinates())) {
                CONSOLE.printCommandError("ошибка в поле Coordinates у объекта: " + flat.getName() +
                        ". Повторите ввод.");
                flat.setCoordinates(FLAT_READER.readCoordinates());
            }
            if (!FlatChecker.checkCreationDate(flat.getCreationDate())) {
                flat.updateCreationDateToNow();
                CONSOLE.printCommandError("ошибка в поле CreationDate у объекта: " + flat.getName() +
                        ". Значение поля переустановлено.");
            }
            if (!FlatChecker.checkArea(flat.getArea())) {
                CONSOLE.printCommandError("ошибка в поле Area у объекта: " + flat.getName() +
                        ". Повторите ввод.");
                flat.setArea(FLAT_READER.readArea());
            }
            if (!FlatChecker.checkNumberOfRooms(flat.getNumberOfRooms())) {
                CONSOLE.printCommandError("ошибка в поле NumberOfRooms у объекта: " + flat.getName() +
                        ". Повторите ввод.");
                flat.setNumberOfRooms(FLAT_READER.readNumberOfRooms());
            }
            if (!FlatChecker.checkNumberOfBathrooms(flat.getNumberOfBathrooms())) {
                CONSOLE.printCommandError("ошибка в поле NumberOfBathrooms у объекта: " + flat.getName() +
                        ". Повторите ввод.");
                flat.setNumberOfRooms(FLAT_READER.readNumberOfBathrooms());
            }
            if (!FlatChecker.checkFurnish(flat.getFurnish())) {
                CONSOLE.printCommandError("ошибка в поле Furnish у объекта: " + flat.getName() +
                        ". Повторите ввод.");
                flat.setFurnish(FLAT_READER.readFurnish());
            }
            if (!FlatChecker.checkView(flat.getView())) {
                CONSOLE.printCommandError("ошибка в поле View у объекта: " + flat.getName() +
                        ". Повторите ввод.");
                flat.setView(FLAT_READER.readView());
            }
            if (!FlatChecker.checkHouse(flat.getHouse())) {
                CONSOLE.printCommandError("ошибка в поле House у объекта: " + flat.getName() +
                        ". Повторите ввод.");
                flat.setHouse(FLAT_READER.readHouse());
            }
        }
        CONSOLE.printCommandTextNext("Файл прошел проверку!");
    }


    private static class FlatChecker {
        private static final HashSet<Integer> ID_LIST = new HashSet<>();

        private static boolean checkId(int id) {
            if (ID_LIST.contains(id) || id < 1 || id > CollectionManager.MAX_ID) return false;
            ID_LIST.add(id);
            return true;
        }

        private static boolean checkName(String name) {
            if (name != null) return name.matches(FlatReader.PATTERN_NAMES);
            return false;
        }

        private static boolean checkCoordinates(Coordinates coordinates) {
            if (coordinates != null) return CoordinatesChecker.checkCoordinateX(coordinates.getX()) &&
                    CoordinatesChecker.checkCoordinateY(coordinates.getY());
            return false;
        }

        private static boolean checkCreationDate(LocalDateTime date) {
            if (date != null) return date.isBefore(LocalDateTime.now());
            return false;
        }

        private static boolean checkArea(int area) {
            return (area > 0);
        }

        private static boolean checkNumberOfRooms(long numberOfRooms) {
            return (numberOfRooms <= 14 && numberOfRooms > 0);
        }

        private static boolean checkNumberOfBathrooms(long numberOfBathrooms) {
            return (numberOfBathrooms > 0);
        }

        private static boolean checkFurnish(Furnish furnish) {
            return (furnish != null);
        }

        private static boolean checkView(View view) {
            return (view != null);
        }

        private static boolean checkHouse(House house) {
            if (house != null) return HouseChecker.checkName(house.getName()) &&
                    HouseChecker.checkYear(house.getYear()) &&
                    HouseChecker.checkNumberOfFloors(house.getNumberOfFloors()) &&
                    HouseChecker.checkNumberOfFlatsOnFloor(house.getNumberOfFlatsOnFloor()) &&
                    HouseChecker.checkNumberOfLifts(house.getNumberOfLifts());
            return false;
        }
    }

    private static class CoordinatesChecker {
        private static boolean checkCoordinateX(int x) {
            return (x <= 713);
        }

        private static boolean checkCoordinateY(Integer y) {
            if (y != null) return (y >= -397);
            return false;
        }
    }

    private static class HouseChecker {
        private static boolean checkName(String name) {
            if (name != null) return name.matches(FlatReader.PATTERN_NAMES);
            return false;
        }

        private static boolean checkYear(int year) {
            return (year > 0);
        }

        private static boolean checkNumberOfFloors(Long numberOfFloors) {
            if (numberOfFloors != null) return (numberOfFloors > 0 && numberOfFloors <= 39);
            return true;
        }

        private static boolean checkNumberOfFlatsOnFloor(long numberOfFlatsOnFloor) {
            return (numberOfFlatsOnFloor > 0);
        }

        private static boolean checkNumberOfLifts(Long numberOfLifts) {
            if (numberOfLifts != null) return (numberOfLifts > 0);
            return true;
        }
    }

}
