package com.example.kalah.model.house;

import com.example.kalah.model.player.Player;

/**
 * Board House implementation class
 */
public class House {

    private final int id;
    private final HouseType houseType;
    private final Player player;
    private int seeds;

    public House(int id, HouseType houseType, int seedsPerPlayer, Player player) {
        this.id = id;
        this.houseType = houseType;
        this.player = player;
        this.seeds = seedsPerPlayer;
    }

    /**
     * Adds a number of seeds to this house
     * @param seeds the number of seeds to add
     */
    public void addSeeds(int seeds) {
        this.seeds = this.seeds + seeds;
    }

    public int getId() {
        return id;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public Player getPlayer() {
        return player;
    }

    public int getSeeds() {
        return seeds;
    }

    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }
}
