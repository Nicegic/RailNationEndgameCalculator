package entities;

public class City {


    public double currentValue, demand;
    public int capacity;
    private int timeToConsume, timeCache;
    private CityName cityName;

    public City(CityName cityName, int capacity, int timeToConsume){
        this.cityName = cityName;
        this.capacity = capacity;
        this.timeToConsume = timeToConsume;
        timeCache = timeToConsume;
        demand = 0.15;
    }

    public void consume(){
        if(timeToConsume==0){
            if(currentValue<capacity) {
                currentValue = currentValue - (currentValue * demand);
                timeToConsume = 15;
            }
        }else
            timeToConsume--;
    }

    public void refresh(){
        timeToConsume = timeCache;
        currentValue = 0.0;
    }
}
