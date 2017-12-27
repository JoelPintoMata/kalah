package com.example.kalah.model.house;

import com.example.kalah.model.player.Player;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Board House implementation class
 */
@EqualsAndHashCode
public class House {

    @Getter
    private final int id;
    @Getter
    private final HouseType houseType;
    @Getter
    private final Player player;
    @Getter@Setter
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
}
