package entities;

public class Track {

    private int distance, order;
    private Plant plant;


    public Track(int length, Plant plant, int order){
        distance = length*150;
        this.plant = plant;
        this.order = order;
    }

    public int getDistance(){
        return distance;
    }

    public Plant getPlant(){
        return plant;
    }

    public String getName(){
        return order+". "+plant.toString() + "( "+distance+"m)";
    }

}
