package cal;

import entities.City;
import entities.Plant;
import entities.Track;

import javax.swing.*;
import java.util.ArrayList;

public class FinishCal extends Thread implements Calculator{

    private City city;
    private ArrayList<Track> tracksForCity;
    private ArrayList<Integer> times;
    private CapacityCalculator calculator;
    private Object[][] durations;
    private Controller handler;
    private final Object lock = new Object();
    private int threads, threadsFinished;
    private CalculatorThread[] calThreads;

    FinishCal(City city, ArrayList<Track> tracks, ArrayList<Integer> times, Controller handler){
        this.city = city;
        tracksForCity = tracks;
        this.times = times;
        calculator = new CapacityCalculator();
        durations = new Object[tracks.size()][times.size()+1];
        this.handler = handler;
    }

    void changeNumberOfPlayers(int number){
        calculator.setNumberOfPlayers(number);
    }

    void changeCityCapacity(int capacity){
        city.capacity = capacity;
    }

    void setDemand(double demand){city.demand = demand; }

    public void /*calculate()*/ run(){
        JProgressBar progressBar = handler.enableStatusUpdate("finishCal");
        /*int part = (numberOfTracks/cores);
        int trow=0, brow=0;
        durations = new Object[tracks.size()][times.size() + 1];
        for(threads=0;threads<cores;threads++){
            trow = x*part;
            brow = ((threads+1)*part)-1;
            ArrayList<Track> subtracks = new ArrayList<>(tracks.subList(trow, brow));
            new CalculatorThread(calculator,subtracks, times, city, currentCityDemand, trow, brow, this, threads).start();
        }
        if(brow<numberOfTracks-1){
            trow = brow+1;
            brow = numberOfTracks-1;
            ArrayList<Track> subtracks = new ArrayList<>(tracks.subList(trow, brow));
            new CalculatorThread(calculator,subtracks, times, city, currentCityDemand, trow, brow, this, x).start();
            threads++;
        }*/
        durations = new Object[tracksForCity.size()][times.size()+1];
        for(int i=0;i<tracksForCity.size();i++){
            for(int j=0;j<(times.size()+1);j++){
                if(j==0)
                    durations[i][j] = tracksForCity.get(i).getName();
                else
                    durations[i][j]=calculateTimeForFinish(tracksForCity.get(i), times.get(j-1));
                if(isInterrupted())
                    break;
            }
            progressBar.setValue(i);
            if(isInterrupted())
                break;
        }
        if(isInterrupted())
            System.out.println("Interrupted Calculation");
        else
            handler.finished(durations, "finishCal");
    }

    private int calculateTimeForFinish(Track track, int waitTime){
        int rawTime=0;
        city.refresh();
        Plant plant = track.getPlant();
        plant.refresh();
        while(city.currentValue<city.capacity){
            city.currentValue += calculator.getCapacity(track, waitTime, 1);
            city.consume();
            plant.recalculate();
            rawTime++;
            if(isInterrupted()){
                break;
            }
            if(rawTime>120){
                return 0;
            }
        }
        return rawTime;
    }

    void addDataToTableData(Object[][] durations, int trow, int brow){
        synchronized(lock) {
            for (int i = trow; i < brow; i++) {
                System.arraycopy(durations[i - trow], 0, this.durations[i], 0, 16);
            }
            threadsFinished++;
        }
        if(threadsFinished==threads){
            handler.finished(durations, "finishCal");
        }
    }

    void giveStatusUpdate(int status){
        // TODO: write status Update for Multi-Threading
    }

}
