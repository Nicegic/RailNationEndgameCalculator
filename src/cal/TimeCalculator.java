package cal;

import entities.Track;
import entities.trains.Train;

class TimeCalculator {

    private Track track;
    private Train train;
    private MovementCal mcal;

    TimeCalculator(){
        mcal = new MovementCal();
    }

    void setTrack(Track track){
        this.track = track;
    }

    void setTrain(Train train){
        this.train=train;
    }

    int getTime(int waitTime){
        int duration;
        mcal.setTrack(track);
        mcal.setTrain(train);
        duration=mcal.calculateDurationForTrack();
        duration*=2;
        duration+=waitTime;
        return duration;
    }

}
