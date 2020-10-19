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
        if (id != car.getId()) return false;
        if (brand != null && (!brand.equals(getBrand()) || brand == null)) return false;
        if (model != null && (!model.equals(getModel()) || model == null)) return false;
        if (doors != getDoors()) return false;
        if (passengers != getPassengers()) return false;
        if (cost != car.getCost()) return false;
        return transmission == null || (model.equals(getTransmission()) && transmission != null);
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
