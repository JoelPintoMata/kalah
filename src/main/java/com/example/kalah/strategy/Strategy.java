package com.example.kalah.strategy;

import com.example.kalah.model.board.BoardException;
import com.example.kalah.model.board.House;
import com.example.kalah.model.player.Player;

import java.util.List;

public interface Strategy {

    /**
     * Setup/initialize this strategy
     * @param level this game level
     */
    void setup(int level);

    /**
     * Performs a play on a board following a specific strategy
     * @param playerId the player id
     * @param position the start position
     */
    void play(int playerId, int position) throws BoardException;

    /**
     * Gets this strategy board list of houses
     * @return this strategy board list of houses
     */
    List<House> getBoardHouses();

    /**
     * Gets the winning player
     * @return the winning player
     */
    Player getWinner();

    /**
     * Gets the first player
     * @return the first player
     */
    Player getFirstPlayer();

    /**
     * Gets the next player
     * @return the next player
     */
    Player getNextPlayer();
}