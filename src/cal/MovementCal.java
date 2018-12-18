package cal;

import entities.Track;
import entities.trains.Train;

public class MovementCal {

    Track track;
    Train train;

    public void setTrain(Train train){
        this.train=train;
    }

    public void setTrack(Track track){
        this.track=track;
    }

    public int calculateDurationForTrack(){
        double trainDistance=0;
        int seconds=0;
        train.start();
        while(trainDistance<track.getDistance()){
            train.accelerate();
            trainDistance+=train.distanceMoved();
            seconds++;
        }
        return seconds;
    }
}
