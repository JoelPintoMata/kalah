package com.example.kalah.model.player;

import lombok.Getter;
import lombok.Setter;

public class Player {

    @Setter@Getter
    private int id;

    @Getter
    private int seedsCaptured;

    public void incSeedsCaptured(){
        this.seedsCaptured++;
    }
}
