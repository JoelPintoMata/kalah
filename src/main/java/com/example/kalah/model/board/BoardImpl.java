package com.example.kalah.model.board;

import com.example.kalah.model.player.Player;
import com.example.kalah.model.player.Players;
import com.example.kalah.strategy.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class BoardImpl implements Board {

    private Strategy strategy;

    @Autowired
    private Players players;

    private List<House> houses;

    private int level;

    @Override
    public void setup(int level, int seedsPerPlayer) {
        this.level = level;

        houses = new LinkedList<>();

        int i=0;
        Player player = players.getPlayerOne();
//        initialize player one houses
        for(; i<level; i++) {
            houses.add(new House(i, HouseType.HOUSE, seedsPerPlayer, player));
        }
//        initialize player one store
        houses.add(new House(i++, HouseType.STORE, 0, player));

        player = players.getNext(player);
//        initialize player two houses
        for(; i<(level*2)+1; i++) {
            houses.add(new House(i, HouseType.HOUSE, seedsPerPlayer, player));
        }

//        initialize player two store
        houses.add(new House(i, HouseType.STORE, 0, player));
    }

    @Override
    public List<House> getHouses() {
        return this.houses;
    }

    @Override
    public int getOpponentSeeds(int position) {
        if(getHouses().get(position).getPlayer().equals(players.getPlayerOne()))
            return houses.get(position + level).getSeeds();
        else
            return houses.get(position - level).getSeeds();
    }

    @Override
    public void setCapturedSeeds(Player player, int seeds) {
        if(player.equals(players.getPlayerOne()))
            houses.get(0).setSeeds(houses.get(0).getSeeds() + seeds);
        else
            houses.get(level).setSeeds(houses.get(level).getSeeds() + seeds);
    }
}
