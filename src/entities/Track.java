package entities;

public class Track {

    int length, distance, order;
    Product product;
    City city;
    Orientation orientation;


    public Track(int length, Product product, City city, Orientation orientation, int order){
        this.length=length;
        distance = length*150;
        this.product = product;
        this.city = city;
        this.orientation = orientation;
        this.order = order;
    }

    public int getDistance(){
        return distance;
    }

    public String getName(){
        return order+". "+product.toString()+" | "+city.toString()+" | "+ orientation.toString() +" ("+distance+"m)";
    }

}
