package entities;

import cal.TrainCapacity;
import entities.trains.Train;
import entities.trains.Olymp;

import java.util.ArrayList;

public class Player {

    private ArrayList<Train> trains;
    private TrainCapacity tcapacity;

    public Player(boolean olympDriver){
        trains = new ArrayList<>();
        if(olympDriver){
            hasUpgradedOlymps();
        }
        tcapacity = new TrainCapacity();
    }

    public void buyTrain(Train train){
        trains.add(train);
    }

    public void hasOlymps(){
        for(int i=0;i<5;i++){
            trains.add(new Olymp());
        }
    }

    private void hasUpgradedOlymps(){
        for(int i=0;i<5;i++){
            Olymp o = new Olymp();
            o.upgradeTrain();
            trains.add(o);
        }
    }

    public double getCapacity(Track track, int waitTime, int minutes){
        double capacity=0;
        for(Train train:trains){
            capacity += tcapacity.getCapacity(track, train, waitTime, minutes);
        }
        return capacity;
    }
}
