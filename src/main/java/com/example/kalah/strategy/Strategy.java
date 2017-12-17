package com.example.kalah.strategy;

import com.example.kalah.gameboard.House;
import com.example.kalah.gameboard.BoardException;
import com.example.kalah.model.player.Player;

import java.util.List;

public interface Strategy {

    /**
     * Performs a play on a board following a specific strategy
     * @param playerId the player id
     * @param position the start position
     */
    void play(int playerId, int position) throws BoardException;

    Player getWinner();

    void setup(int level);

    List<House> getBoard();

    Player getFirstPlayer();

    Player getNextPlayer();
}