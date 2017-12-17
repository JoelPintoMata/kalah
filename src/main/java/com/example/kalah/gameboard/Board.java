package com.example.kalah.gameboard;

import com.example.kalah.model.player.Player;

import java.util.List;

/**
 * A game board
 */
public interface Board {

	/**
	 * Generates a game board
	 * @param level the difficulty level
	 * @param strategy the initial number of seeds per player
	 */
    void setupBoard(int level, int strategy);

    void setCapturedSeeds(Player player, int i);

    int getOpponentSeeds(int position);

	List<House> getHouses();
}