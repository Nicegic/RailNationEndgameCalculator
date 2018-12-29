package cal;

import entities.City;
import entities.CityName;
import entities.Plant;
import entities.Track;
import gui.Window;

import javax.swing.*;
import java.util.ArrayList;

import static entities.CityName.*;
import static entities.Orientation.*;
import static entities.Product.*;

public class Controller {

    private ArrayList<Track> tracks = new ArrayList<>();
    private ArrayList<Integer> times;
    public int numberOfTracks=0;
    private Window window;
    private final Object lock= new Object();
    private FinishCal calculator;
    private int recalculationTime;
    public boolean calculationRunning = false;
    private CityName city;

    public Controller(Window window){
        times = new ArrayList<>();
        populateWaitTimes();
        this.window = window;
    }

    public void initializeFinishCalculator(int capacity, int players, double demand, int timeToConsume){
        calculator = new FinishCal(new City(city, capacity, timeToConsume),tracks,times,this);
        calculator.setDemand(demand);
        calculator.changeNumberOfPlayers(players);
    }

    public void startFinishCalculation(){
        calculationRunning = true;
        calculator.start();
    }

    public void stopCalculation(){
        calculator.interrupt();
        calculationRunning = false;
    }

    public void setCity(CityName name){
        city = name;
        tracks.clear();
        populateCityTracks(name);
    }

    public void setRecalculationTime(int time){
        recalculationTime = time;
    }

    private void populateWaitTimes(){
        times.add(0);
        times.add(15);
        times.add(30);
        times.add(45);
        times.add(60);
        times.add(75);
        times.add(90);
        times.add(105);
        times.add(120);
        times.add(135);
        times.add(150);
        times.add(180);
        times.add(210);
        times.add(240);
        times.add(300);
    }

    private void populateForBoston(){
        tracks.add(new Track(3, new Plant(Kohle, Boston, Ost, 10,6000, recalculationTime),1));
        tracks.add(new Track(4, new Plant(Holz, Boston, Nordwest, 10, 6000, recalculationTime),1));
        tracks.add(new Track(4, new Plant(Getreide, Boston, Süd, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(8, new Plant(Eisenerz, Boston, Nord, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(9, new Plant(Eisen, Boston, Nordost, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(9, new Plant(Bretter, Boston, Nordwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(9, new Plant(Stoff, Boston, Südost, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(10, new Plant(Mehl, Boston, West, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(10, new Plant(Papier, Boston, Nordwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(11, new Plant(Rinder, Boston, Süd, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(11, new Plant(Silizium, Boston, Südwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(13, new Plant(Garn, Boston, Südost, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(14, new Plant(Stahl, Boston, Nord, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(14, new Plant(Edelstahl, Boston, Nordwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(15, new Plant(Eisenwaren, Boston, Nordost, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(15, new Plant(Verpackung, Boston, Nordwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(15, new Plant(Leder, Boston, Süd, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(15, new Plant(Chips, Boston, West, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(16, new Plant(Kupfer, Boston, Nord, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(16, new Plant(Fleisch, Boston, Süd, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(16, new Plant(Quarz, Boston, Südwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(18, new Plant(Baumwolle, Boston, Südost, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(18, new Plant(Lebensmittel, Boston, Südwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(18, new Plant(Brot, Boston, West, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(18, new Plant(Öl, Boston, Nordwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(19, new Plant(Chemikalien, Boston, Nordwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(20, new Plant(Rohre, Boston, Nordost, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(20, new Plant(Bauxit, Boston, Nordwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(20, new Plant(Schuhe, Boston, Süd, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(21, new Plant(Lampen, Boston, Nord, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(21, new Plant(Draht, Boston, Nord, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(21, new Plant(Stoff, NewYork, Nordost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(21, new Plant(Stahl, NewYork, Nord, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(22, new Plant(Kupfererz, Boston, Nord, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(22, new Plant(Benzin, Boston, Nordwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(22, new Plant(Glas, Boston, Südwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(23, new Plant(Keramik, Boston, Südwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(23, new Plant(Töpfe, Boston, West, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(24, new Plant(Aluminium, Boston, Nordwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(24, new Plant(Edelstahl, Buffalo, Ost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(25, new Plant(Wannen, NewYork, Nordwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(25, new Plant(Kleidung, NewYork, Nordost, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(26, new Plant(Bleche, Boston, Nordost, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(27, new Plant(Stahlträger, Boston, Nord, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(27, new Plant(Plastik, Boston, Nordwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(27, new Plant(Garn, NewYork, Nordost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(28, new Plant(Fenster, NewYork, Nord, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(28, new Plant(Pillen, Boston, Nord, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(29, new Plant(Lampen, NewYork, Nordwest, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(29, new Plant(Eisen, NewYork, Nord, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(31, new Plant(Maschinen, Boston, Nord, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(31, new Plant(Baumwolle, NewYork, Nordost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(32, new Plant(Bälle, Boston, Nordwest, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(32, new Plant(Autos, Buffalo, Ost, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(33, new Plant(Eisenerz, NewYork, Nordwest, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(34, new Plant(Wannen, Buffalo, Südost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(35, new Plant(Bälle, Buffalo, Südost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(36, new Plant(Eisenwaren, NewYork, Nordost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(37, new Plant(Spielzeug, Buffalo, Ost, 10, 6000, recalculationTime), 1));
        tracks.add(new Track(37, new Plant(Kohle, NewYork, Nord, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(38, new Plant(Bretter, NewYork, Ost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(40, new Plant(Leder, NewYork, West, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(42, new Plant(Fenster, Buffalo, Nordost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(42, new Plant(Bauxit, Buffalo, Südost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(42, new Plant(Getreide, NewYork, West, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(43, new Plant(Bleche, Buffalo, Ost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(43, new Plant(Holz, NewYork, Ost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(44, new Plant(Verpackung, Buffalo, Ost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(46, new Plant(Aluminium, Buffalo, Südost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(47, new Plant(Mehl, NewYork, Süd, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(47, new Plant(Rinder, NewYork, West, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(48, new Plant(Brot, Buffalo, Nord, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(48, new Plant(Glas, NewYork, Nordost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(48, new Plant(Papier, NewYork, Nordost, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Silizium, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Chips, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Kupfer, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Fleisch, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Quarz, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Lebensmittel, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Öl, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Chemikalien, Buffalo, Lager,10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Rohre, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Schuhe, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Draht, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Kupfererz, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Benzin, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Keramik, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Töpfe, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Kleidung, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Stahlträger, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Plastik, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Maschinen, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Autos, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Spielzeug, Buffalo, Lager, 10, 6000, recalculationTime), 2));
        tracks.add(new Track(50, new Plant(Pillen, Buffalo, Lager, 10, 6000, recalculationTime), 2));
    }

    private void populateCityTracks(CityName city){
        switch(city){
            case Boston:
                populateForBoston();
                break;
            case NewYork:
                break;
        }
        numberOfTracks = tracks.size();
    }

    void finished(Object[][] durations, String calculation){
        window.calculationDone(durations, findTab(calculation));
        calculationRunning=false;
    }

    JProgressBar enableStatusUpdate(String calculation){
        return window.getProgressBar(findTab(calculation));
    }

    String findTab(String calculation){
        switch(calculation){
            case "finishCal":
                return "FinishCalGUI";
            default:
                return "";
        }
    }
}
