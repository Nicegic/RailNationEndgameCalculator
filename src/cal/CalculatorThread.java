package cal;

import entities.City;
import entities.Track;

import java.util.ArrayList;

public class CalculatorThread extends Thread {

    private CapacityCalculator calculator;
    private ArrayList<Track> tracks;
    private ArrayList<Integer> times;
    private City city;
    private int trow, brow;
    private FinishCal handler;

    public CalculatorThread(CapacityCalculator calculator, ArrayList<Track> tracks, ArrayList<Integer> times, City city, int trow, int brow, FinishCal handler){
        super();
        this.calculator = calculator;
        this.tracks = tracks;
        this.times = times;
        this.city = city;
        this.trow = trow;
        this.brow = brow;
        this.handler = handler;
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
            handler.giveStatusUpdate(i);
        }
        handler.addDataToTableData(durations, trow, brow);
    }

    private int calculateTimeForFinish(Track track, int waitTime){
        int rawTime=0;
        while(city.currentValue<city.capacity){
            city.currentValue = calculator.getCapacity(track, waitTime, 1);
            city.consume();
            track.getPlant().recalculate();
            rawTime++;
        }
        return rawTime;
    }

}
