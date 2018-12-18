package cal;

import entities.City;
import entities.CityCap;
import entities.Track;
import java.util.ArrayList;

import static entities.City.*;
import static entities.Orientation.*;
import static entities.Product.*;

public class CapacityOverview {

    private CapacityCalculator calculator;
    private ArrayList<Track> tracks = new ArrayList<>();
    private ArrayList<Integer> times;
    private CityCap city;
    private double currentCityDemand = 0.15;

    public CapacityOverview(City city){
        calculator = new CapacityCalculator();
        times = new ArrayList<>();
        populateCityTracks(city);
        populateWaitTimes();
        this.city = new CityCap(city);
    }

    public Object[][] getTableData(){
        Object[][] durations = new Object[tracks.size()][times.size()+1];
        for(int i=0;i<tracks.size();i++){
            for(int j=0;j<times.size()+1;j++){
                if(j==0)
                    durations[i][j] = tracks.get(i).getName();
                else
                    durations[i][j]=calculateTimeForFinish(tracks.get(i), times.get(j-1));
            }
        }
        return durations;
    }

    public void changeNumberOfPlayers(int number){
        calculator.setNumberOfPlayers(number);
    }

    public void setCitycapacity(int capacity){
        city.setCapacity(capacity);
    }

    public void setCurrentCityDemand(double demand) { currentCityDemand = demand; }

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
        tracks.add(new Track(3, Kohle, Boston, Ost,1));
        tracks.add(new Track(4, Holz, Boston, Nordwest, 1));
        tracks.add(new Track(4, Getreide, Boston, Süd, 1));
        tracks.add(new Track(8, Eisenerz, Boston, Nord, 1));
        tracks.add(new Track(9, Eisen, Boston, Nordost, 1));
        tracks.add(new Track(9, Bretter, Boston, Nordwest, 1));
        tracks.add(new Track(9, Stoff, Boston, Südost, 1));
        tracks.add(new Track(10, Mehl, Boston, West, 1));
        tracks.add(new Track(10, Papier, Boston, Nordwest, 1));
        tracks.add(new Track(11, Rinder, Boston, Süd, 1));
        tracks.add(new Track(11, Silizium, Boston, Südwest, 1));
        tracks.add(new Track(13, Garn, Boston, Südost, 1));
        tracks.add(new Track(14, Stahl, Boston, Nord, 1));
        tracks.add(new Track(14, Edelstahl, Boston, Nordwest, 1));
        tracks.add(new Track(15, Eisenwaren, Boston, Nordost, 1));
        tracks.add(new Track(15, Verpackung, Boston, Nordwest, 1));
        tracks.add(new Track(15, Leder, Boston, Süd, 1));
        tracks.add(new Track(15, Chips, Boston, West, 1));
        tracks.add(new Track(16, Kupfer, Boston, Nord, 1));
        tracks.add(new Track(16, Fleisch, Boston, Süd, 1));
        tracks.add(new Track(16, Quarz, Boston, Südwest, 1));
        tracks.add(new Track(18, Baumwolle, Boston, Südost, 1));
        tracks.add(new Track(18, Lebensmittel, Boston, Südwest, 1));
        tracks.add(new Track(18, Brot, Boston, West, 1));
        tracks.add(new Track(18, Öl, Boston, Nordwest, 1));
        tracks.add(new Track(19, Chemikalien, Boston, Nordwest, 1));
        tracks.add(new Track(20, Rohre, Boston, Nordost, 1));
        tracks.add(new Track(20, Bauxit, Boston, Nordwest, 1));
        tracks.add(new Track(20, Schuhe, Boston, Süd, 1));
        tracks.add(new Track(21, Lampen, Boston, Nord, 1));
        tracks.add(new Track(21, Draht, Boston, Nord, 1));
        tracks.add(new Track(21, Stoff, NewYork, Nordost, 2));
        tracks.add(new Track(21, Stahl, NewYork, Nord, 2));
        tracks.add(new Track(22, Kupfererz, Boston, Nord, 1));
        tracks.add(new Track(22, Benzin, Boston, Nordwest, 1));
        tracks.add(new Track(22, Glas, Boston, Südwest, 1));
        tracks.add(new Track(23, Keramik, Boston, Südwest, 1));
        tracks.add(new Track(23, Töpfe, Boston, West, 1));
        tracks.add(new Track(24, Aluminium, Boston, Nordwest, 1));
        tracks.add(new Track(24, Edelstahl, Buffalo, Ost, 2));
        tracks.add(new Track(25, Wannen, NewYork, Nordwest, 1));
        tracks.add(new Track(25, Kleidung, NewYork, Nordost, 1));
        tracks.add(new Track(26, Bleche, Boston, Nordost, 1));
        tracks.add(new Track(27, Stahlträger, Boston, Nord, 1));
        tracks.add(new Track(27, Plastik, Boston, Nordwest, 1));
        tracks.add(new Track(27, Garn, NewYork, Nordost, 2));
        tracks.add(new Track(28, Fenster, NewYork, Nord, 1));
        tracks.add(new Track(28, Pillen, Boston, Nord, 1));
        tracks.add(new Track(29, Lampen, NewYork, Nordwest, 2));
        tracks.add(new Track(29, Eisen, NewYork, Nord, 2));
        tracks.add(new Track(31, Maschinen, Boston, Nord, 1));
        tracks.add(new Track(31, Baumwolle, NewYork, Nordost, 2));
        tracks.add(new Track(32, Bälle, Boston, Nordwest, 1));
        tracks.add(new Track(32, Autos, Buffalo, Ost, 1));
        tracks.add(new Track(33, Eisenerz, NewYork, Nordwest, 2));
        tracks.add(new Track(34, Wannen, Buffalo, Südost, 2));
        tracks.add(new Track(35, Bälle, Buffalo, Südost, 2));
        tracks.add(new Track(36, Eisenwaren, NewYork, Nordost, 2));
        tracks.add(new Track(37, Spielzeug, Buffalo, Ost, 1));
        tracks.add(new Track(37, Kohle, NewYork, Nord, 2));
        tracks.add(new Track(38, Bretter, NewYork, Ost, 2));
        tracks.add(new Track(40, Leder, NewYork, West, 2));
        tracks.add(new Track(42, Fenster, Buffalo, Nordost, 2));
        tracks.add(new Track(42, Bauxit, Buffalo, Südost, 2));
        tracks.add(new Track(42, Getreide, NewYork, West, 2));
        tracks.add(new Track(43, Bleche, Buffalo, Ost, 2));
        tracks.add(new Track(43, Holz, NewYork, Ost, 2));
        tracks.add(new Track(44, Verpackung, Buffalo, Ost, 2));
        tracks.add(new Track(46, Aluminium, Buffalo, Südost, 2));
        tracks.add(new Track(47, Mehl, NewYork, Süd, 2));
        tracks.add(new Track(47, Rinder, NewYork, West, 2));
        tracks.add(new Track(48, Brot, Buffalo, Nord, 2));
        tracks.add(new Track(48, Glas, NewYork, Nordost, 2));
        tracks.add(new Track(48, Papier, NewYork, Nordost, 2));
        tracks.add(new Track(50, Silizium, Buffalo, Lager, 2));
        tracks.add(new Track(50, Chips, Buffalo, Lager, 2));
        tracks.add(new Track(50, Kupfer, Buffalo, Lager, 2));
        tracks.add(new Track(50, Fleisch, Buffalo, Lager, 2));
        tracks.add(new Track(50, Quarz, Buffalo, Lager, 2));
        tracks.add(new Track(50, Lebensmittel, Buffalo, Lager, 2));
        tracks.add(new Track(50, Öl, Buffalo, Lager, 2));
        tracks.add(new Track(50, Chemikalien, Buffalo, Lager, 2));
        tracks.add(new Track(50, Rohre, Buffalo, Lager, 2));
        tracks.add(new Track(50, Schuhe, Buffalo, Lager, 2));
        tracks.add(new Track(50, Draht, Buffalo, Lager, 2));
        tracks.add(new Track(50, Kupfererz, Buffalo, Lager, 2));
        tracks.add(new Track(50, Benzin, Buffalo, Lager, 2));
        tracks.add(new Track(50, Keramik, Buffalo, Lager, 2));
        tracks.add(new Track(50, Töpfe, Buffalo, Lager, 2));
        tracks.add(new Track(50, Kleidung, Buffalo, Lager, 2));
        tracks.add(new Track(50, Stahlträger, Buffalo, Lager, 2));
        tracks.add(new Track(50, Plastik, Buffalo, Lager, 2));
        tracks.add(new Track(50, Maschinen, Buffalo, Lager, 2));
        tracks.add(new Track(50, Autos, Buffalo, Lager, 2));
        tracks.add(new Track(50, Spielzeug, Buffalo, Lager, 2));
        tracks.add(new Track(50, Pillen, Buffalo, Lager, 2));
    }

    private void populateCityTracks(City city){
        switch(city){
            case Boston:
                populateForBoston();
                break;
            case NewYork:
                break;
        }
    }

    private int calculateTimeForFinish(Track track, int waitTime){
        double capacityToDo = city.capacity();
        double currentCapacity = 0, help;
        int rawTime=0;
        while(currentCapacity<capacityToDo){
            currentCapacity+= calculator.getCapacity(track, waitTime, 1);
            rawTime++;
            if(rawTime%15 == 0){
                currentCapacity= currentCapacity-(currentCapacity*currentCityDemand);
            }
            if(rawTime>300){
                return -1;
            }
        }
        return rawTime;
    }
}
