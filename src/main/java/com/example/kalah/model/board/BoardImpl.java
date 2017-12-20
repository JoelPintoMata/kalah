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
    public void captureSeeds(Player player, int seeds) {
        if(player.equals(players.getPlayerOne())) {
            houses.get(level).setSeeds(houses.get(level).getSeeds() + seeds);
        }
        else {
            houses.get((level * 2) + 1).setSeeds(houses.get((level * 2) + 1).getSeeds() + seeds);
        }
    }

    public int getLevel() {
        return level;
    }
}
