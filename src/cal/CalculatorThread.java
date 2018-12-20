package cal;

import entities.CityCap;
import entities.Track;

import java.util.ArrayList;

public class CalculatorThread extends Thread {

    private CapacityCalculator calculator;
    private ArrayList<Track> tracks;
    private ArrayList<Integer> times;
    private CityCap city;
    private double currentCityDemand;
    private int trow, brow, nr;
    private CapacityOverview handler;

    public CalculatorThread(CapacityCalculator calculator, ArrayList<Track> tracks, ArrayList<Integer> times, CityCap city, double currentCityDemand, int trow, int brow, CapacityOverview handler, int nr){
        super();
        this.calculator = calculator;
        this.tracks = tracks;
        this.times = times;
        this.city = city;
        this.currentCityDemand = currentCityDemand;
        this.trow = trow;
        this.brow = brow;
        this.handler = handler;
        this.nr=nr;
    }


    @Override
    public void run(){
        Object[][] durations = new Object[tracks.size()][times.size()+1];
        for(int i=0;i<(tracks.size());i++){
            for(int j=0;j<(times.size()+1);j++){
                if(j==0)
                    durations[i][j] = tracks.get(i).getName();
                else
                    durations[i][j] = calculateTimeForFinish(tracks.get(i),times.get(j-1));
            }
            handler.giveStatusUpdate(i,nr);
        }
        handler.addDataToTableData(durations, trow, brow);
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
