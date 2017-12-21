package com.example.kalah.model.player;

import lombok.Getter;
import lombok.Setter;

/**
 * Player implementation class
 */
public class Player {

    @Setter@Getter
    private int id;

    @Getter
    private int seedsCaptured;

    public void incSeedsCaptured(){
        this.seedsCaptured++;
    }
}
