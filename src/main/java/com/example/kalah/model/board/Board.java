package com.example.kalah.model.board;

import com.example.kalah.model.house.House;
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
	 * Gets this game board houses
	 * @return a list of this game board houses
	 */
	List<House> getHouses();

    /**
     * Gets a player store type house
     * @param player the player
     * @return a player store house
     */
    House getStore(Player player);

	/**
	 *
	 * @return
	 */
	int getLevel();
}