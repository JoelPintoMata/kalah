package com.example.kalah.gameboard;

import com.example.kalah.model.player.Player;

public class House {

    private final HouseType houseType;
    private final Player player;
    private int seeds;

    public House(HouseType houseType, int seedsPerPlayer, Player player) {
        this.houseType = houseType;
        this.player = player;
        this.seeds = seedsPerPlayer;
    }

    public int getSeeds() {
        return this.seeds;
    }

    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }

    public Player getPlayer() {
        return player;
    }

    public HouseType getHouseType() {
        return houseType;
    }
}
