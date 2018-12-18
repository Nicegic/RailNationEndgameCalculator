package cal;

import entities.Track;
import entities.trains.Train;

public class TimeCalculator {

    Track track;
    Train train;
    MovementCal mcal;

    public TimeCalculator(){
        mcal = new MovementCal();
    }

    public void setTrack(Track track){
        this.track = track;
    }

    public void setTrain(Train train){
        this.train=train;
    }

    public int getTime(int waitTime){
        int duration=0;
        mcal.setTrack(track);
        mcal.setTrain(train);
        duration=mcal.calculateDurationForTrack();
        duration*=2;
        duration+=waitTime;
        return duration;
    }

}
