package entities.trains;

public class Train {

    int vmax,accel,power;
    double currentv;
    double health;

    public Train(){
        health=0.95;
    }

    public void accelerate(){
        if(currentv<(vmax*health)){
            currentv+=accel;
        }
    }

    public double distanceMoved(){
        return currentv/3.6;
    }

    public void start(){
        currentv = 0;
    }

    public int getPower(){
        return power;
    }

    public double getHealth(){
        return health;
    }

}
