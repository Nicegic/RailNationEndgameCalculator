package entities;

public class Plant {

    CityName city;
    Orientation orientation;
    Product product;
    public int waitTime, turnover, waitTimeCache;
    private int level, capacity, timeToRecalculation, calculateTimeCache;

    public Plant(Product product, CityName city, Orientation orientation, int level, int capacity, int timeToRecalculation){
        this.city = city;
        this.orientation = orientation;
        this.product = product;
        this.level = level;
        this.capacity = capacity;
        this.timeToRecalculation = timeToRecalculation;
        calculateTimeCache = timeToRecalculation;
    }

    public void recalculate(){
        if(timeToRecalculation == 0){
            calculateWaitTime();
            timeToRecalculation = 60;
        }else{
            timeToRecalculation--;
        }
    }

    private void calculateWaitTime(){
        if(turnover<capacity){
            waitTime = turnover/capacity * 60;
        }else{
            waitTime = capacity/turnover * 60;
        }
        waitTime = turnover/capacity;
    }

    public String toString(){
        return product.toString()+" | "+city.toString()+" | "+ orientation.toString()+" ";
    }

    public void refresh(){
        waitTime = waitTimeCache;
        timeToRecalculation = calculateTimeCache;
    }

}
