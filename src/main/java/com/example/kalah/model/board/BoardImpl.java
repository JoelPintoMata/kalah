package com.example.kalah.model.board;

import com.example.kalah.model.house.House;
import com.example.kalah.model.house.HouseType;
import com.example.kalah.model.player.Player;
import com.example.kalah.model.player.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A game board concrete implementation class
 */
@Component
public class BoardImpl implements Board {

    @Autowired
    private Players players;

    private List<House> houses;

//    maps a player id to its store position
    private Map<Integer, Integer> stores;

    private int level;

    @Override
    public void setup(int level, int seedsPerPlayer) {
        this.level = level;

        houses = new LinkedList<>();
        stores = new HashMap();

        int i=0;
        Player player = players.getPlayerOne();
//        initialize player one houses
        for(; i<level; i++) {
            houses.add(new House(i, HouseType.HOUSE, seedsPerPlayer, player));
        }
//        initialize player one store
        stores.put(player.getId(), i);
        houses.add(new House(i++, HouseType.STORE, 0, player));

        player = players.getNext(player);
//        initialize player two houses
        for(; i<(level*2)+1; i++) {
            houses.add(new House(i, HouseType.HOUSE, seedsPerPlayer, player));
        }

//        initialize player two store
        stores.put(player.getId(), i);
        houses.add(new House(i, HouseType.STORE, 0, player));
    }

    @Override
    public List<House> getHouses() {
        return this.houses;
    }

    @Override
    public House getStore(Player player) {
        return houses.get(stores.get(Integer.valueOf(player.getId())));
    }

    @Override
    public void captureSeeds(Player player, int seeds) {
        int PlayerStorePosition = stores.get(Integer.valueOf(player.getId()));
        House house = houses.get(PlayerStorePosition);
        house.addSeeds(seeds);
    }
}
