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
        List<CarF> carsOld = new SpeedFilter().filter(cars, 180);
        for (CarF c: carsOld){
            System.out.println(c.getSpeed());
        }

        carsOld = new DoorsFilter().filter(carsOld, 4);
        for (CarF c: carsOld){
            System.out.println(c.getColor());
        }

        System.out.println("---------------------------------------------------------");

        AndFilter andFilter = new AndFilter(new SpeedFilter(), new DoorsFilter());
        List<CarF> carsAnd = andFilter.filter(cars, 180, 2);
        for (CarF c: carsOld){
            System.out.println(c.getColor());
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
    List<CarF> filter(List<CarF> cars, T ... t);
}

// фильтр скорости
class SpeedFilter implements CarFilter{
    @Override
    public List<CarF> filter(List cars, Object ... speed) {
        List<CarF> oldList = cars;
        List<CarF> newList = new ArrayList<>();

        for (CarF car:oldList){
            if (car.getSpeed() >= (int)speed[0]){
                newList.add(car);
            }
        }

        return newList;
    }
}

// фильтр дверей
class DoorsFilter implements CarFilter{
    @Override
    public List<CarF> filter(List cars, Object ... doors) {
        List<CarF> oldList = cars;
        List<CarF> newList = new ArrayList<>();

        for (CarF car:oldList){
            if (car.getDoors() >= (int)doors[0]){
                newList.add(car);
            }
        }

        return newList;
    }
}

// совмещение нескольких фильтров
class AndFilter implements CarFilter{
    CarFilter filter1;
    CarFilter filter2;

    public AndFilter(CarFilter filter1, CarFilter filter2) {
        this.filter1 = filter1;
        this.filter2 = filter2;
    }

    @Override
    public List<CarF> filter(List cars, Object ... o) {
        List<CarF> oldList = cars;
        List<CarF> newList = filter1.filter(cars, o[0]);
        return filter2.filter(newList, o[1]);
    }
}

