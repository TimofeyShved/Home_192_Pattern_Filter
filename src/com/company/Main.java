package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // создаем список машин
        CarF carF = new CarF(150, "green", 4);
        CarF carF2 = new CarF(210, "red", 2);
        CarF carF3 = new CarF(190, "black", 4);
        List<CarF> cars = new ArrayList<>();
        cars.add(carF);
        cars.add(carF2);
        cars.add(carF3);

        // запускаем фильтр
        cars = new SpeedFilter().filter(cars, 180);
        for (CarF c: cars){
            System.out.println(c.getSpeed());
        }

        cars = new DoorsFilter().filter(cars, 4);
        for (CarF c: cars){
            System.out.println(c.getDoors());
        }
    }
}

// класс с машинами
class CarF{
    private int speed;
    private String color;
    private int doors;

    public CarF(int speed, String color, int doors) {
        this.speed = speed;
        this.color = color;
        this.doors = doors;
    }

    public int getSpeed() {
        return speed;
    }

    public String getColor() {
        return color;
    }

    public int getDoors() {
        return doors;
    }
}

// --------------------------------------------------------- создаём фильтры
interface CarFilter<T> {
    List<CarF> filter(List<CarF> cars, T t);
}

// фильтр скорости
class SpeedFilter implements CarFilter{
    @Override
    public List<CarF> filter(List cars, Object speed) {
        List<CarF> oldList = cars;
        List<CarF> newList = new ArrayList<>();

        for (CarF car:oldList){
            if (car.getSpeed() >= (int)speed){
                newList.add(car);
            }
        }

        return newList;
    }
}

// фильтр дверей
class DoorsFilter implements CarFilter{
    @Override
    public List<CarF> filter(List cars, Object doors) {
        List<CarF> oldList = cars;
        List<CarF> newList = new ArrayList<>();

        for (CarF car:oldList){
            if (car.getDoors() >= (int)doors){
                newList.add(car);
            }
        }

        return newList;
    }
}
