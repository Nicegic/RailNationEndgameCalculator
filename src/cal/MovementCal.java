package cal;

import entities.Track;
import entities.trains.Train;

class MovementCal {

    private Track track;
    private Train train;

    void setTrain(Train train){
        this.train=train;
    }

    void setTrack(Track track){
        this.track=track;
    }

    int calculateDurationForTrack(){
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
