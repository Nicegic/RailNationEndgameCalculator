package entities;

public class CityCap {

    private int capacity, currentValue;
    private City city;

    public CityCap(City city){
        this.city = city;
    }

    public CityCap(City city, int capacity){
        this.city = city;
        this.capacity = capacity;
    }

    public void setCurrentValue(int currentValue){
        this.currentValue = currentValue;
    }

    public int getCurrentValue(){
        return currentValue;
    }

    public int capacity(){
        return capacity;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

}
