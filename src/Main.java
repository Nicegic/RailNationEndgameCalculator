import cal.CapacityCalculator;
import entities.City;
import entities.Orientation;
import entities.Product;
import entities.Track;
import gui.Window;

public class Main {

    static CapacityCalculator calculator;

    public static void main(String[] args){
        Window window = new Window();
    }

    public static int timeToFinish(int mass, double capacity){
        return (int)(mass/capacity*60);
    }

}
