package cal;

import entities.Track;
import entities.trains.Train;

public class TrainCapacity {

    TimeCalculator tcal;

    public TrainCapacity(){
        tcal = new TimeCalculator();
    }

    public double getCapacity(Track track, Train train, int waitTime, int minutes){
        tcal.setTrack(track);
        tcal.setTrain(train);
        double times = minutes*60.0/tcal.getTime(waitTime);
        return times*train.getPower();
    }
}
