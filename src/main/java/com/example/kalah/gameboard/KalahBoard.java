package com.example.kalah.gameboard;

import com.example.kalah.model.player.Player;

import java.util.List;

/**
 * A game board
 */
public interface KalahBoard {

	/**
	 * Generates a game board
	 * @param level the difficulty level
	 * @param strategy the strategy (rules) to be applied
	 * @return
	 */
    List<Integer> setup(int level, String strategy);

	/**
	 * Checks if the current play is valid
	 * @param position starting position
	 * @return
	 */
	String isValid(int position);

	/**
	 * Sets the played value in the game board
     * @param playerId the player id
     * @param position starting position
     */
    List<Integer> play(int playerId, int position) throws KalahBoardException;

	/**
	 * Checks if the game is won
	 * @return the winning player
	 */
	Player getWinner();
}