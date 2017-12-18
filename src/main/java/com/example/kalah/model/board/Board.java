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

	void setCapturedSeeds(Player player, int i);

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