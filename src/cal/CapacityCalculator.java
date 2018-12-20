package cal;

import entities.Player;
import entities.Track;

import java.util.ArrayList;

public class CapacityCalculator {

    private ArrayList<Player> players;

    CapacityCalculator(){
        players = new ArrayList<>();
    }

    public CapacityCalculator(int numberOfPlayers){
        players = new ArrayList<>();
        for(int i=0;i<numberOfPlayers;i++){
            players.add(new Player(true));
        }
    }

    double getCapacity(Track track, int waitTime, int minutes){
        double capacity =0;
        for(Player p: players){
            capacity+=p.getCapacity(track, waitTime, minutes);
        }
        return capacity;
    }

    private void addPlayer(Player player){
        players.add(player);
    }

    private void removePlayer(Player player){
        players.remove(player);
    }

    void setNumberOfPlayers(int number){
        if(number<players.size()){
            while(number<players.size()&&!players.isEmpty()){
                removePlayer(players.get(players.size()-1));
            }
        }else if(number>players.size()){
            while(number>players.size()){
                addPlayer(new Player(true));
            }
        }
    }

}
