package com.example.kalah.model.board;

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
    void setup(int level, int strategy);

    /**
     * Capture seeds in the player store
     * @param player the player
     * @param numberOfSeeds the number of seeds captured
     */
	void captureSeeds(Player player, int numberOfSeeds);

	/**
	 * Gets the number of seeds on the opposed (opponents) house
	 * @param position the position
	 * @return the number of seeds on the opposed (opponents) house
	 */
    int getOpponentSeeds(int position);

	/**
	 * Gets this game board houses
	 * @return a list of this game board houses
	 */
	List<House> getHouses();
}