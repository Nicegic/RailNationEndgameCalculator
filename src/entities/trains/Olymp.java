package entities.trains;

public class Olymp extends Train{

    private boolean uppow1=false,uppow2=false,upvmax1=false,upvmax2=false,upaccel1=false,upaccel2=false;

    public Olymp(){
        power = 50;
        vmax = 200;
        accel = 12;
    }

    public void upgradeTrain(){
        upgradeAccel1();
        upgradeAccel2();
        upgradePower1();
        upgradePower2();
        upgradeVmax1();
        upgradeVmax2();
    }

    public void upgradePower1(){
        if(!uppow1) {
            power += 10;
            uppow1=true;
        }
    }

    public void upgradePower2(){
        if(!uppow2){
            power +=5;
            uppow2=true;
        }
    }

    public void upgradeVmax1(){
        if(!upvmax1){
            vmax += 25;
            upvmax1=true;
        }
    }

    public void upgradeVmax2(){
        if(!upvmax2){
            vmax += 25;
            upvmax2=true;
        }
    }

    public void upgradeAccel1(){
        if(!upaccel1){
            accel += 2;
            upaccel1=true;
        }
    }

    public void upgradeAccel2(){
        if(!upaccel2){
            accel+=4;
            upaccel2=true;
        }
    }

}
