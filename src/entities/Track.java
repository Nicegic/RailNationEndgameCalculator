package entities;

public class Track {

    private int distance, order;
    private Product product;
    private City city;
    private Orientation orientation;


    public Track(int length, Product product, City city, Orientation orientation, int order){
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
