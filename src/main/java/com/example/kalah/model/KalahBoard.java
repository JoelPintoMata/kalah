package com.example.kalah.model;

/**
 * A game board
 */
public interface KalahBoard {

	/**
	 * Generates a game board
	 */
	public int[][] generate(int difficultyLevel);

	/**
	 * Checks if the current play is valid
	 * @param line, line number
	 * @param column, column number
	 * @return
	 */
	public String isValid(String line, String column);

	/**
	 * Sets the played value in the game board
	 * @param line, line number
	 * @param column, column number
	 * @param value, the value to insert in the given line and column
	 */
	public void play(String line, String column, String value);

	/**
	 * Checks if the game was won
	 * @return
	 */
	public boolean isGameWon();
}