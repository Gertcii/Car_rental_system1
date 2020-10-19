package entity;

import java.util.Objects;

//внесение, сохранение и изменение данных о машине

public class Cars {
    private int id;
    private String brand;
    private String model;
    private int doors;
    private int passengers;
    private int cost;
    private String transmission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Cars car = (Cars) o;
        return id == car.id &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(model, car.model) &&
                Objects.equals(transmission, car.transmission) &&
                doors == car.doors &&
                passengers == car.passengers &&
                cost == car.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, doors, passengers, cost, transmission);
    }

    @Override
    public String toString() {
        return ("Cars id - " + id + ".   " + brand + " | " + model + " | " + doors + " | "
                + passengers + " | " + cost + " | " + transmission);
    }
}
