package com.example.kalah.strategy;

import com.example.kalah.gameboard.KalahBoardException;
import com.example.kalah.model.player.Player;

import java.util.List;

public interface Strategy {

    int doOperation(int num1, int num2);

    /**
     * Performs a play on a board following a specific strategy
     * @param board the game board
     * @param playerId the player id
     * @param position the start position
     */
    List play(List<Integer> board, Player playerId, int position) throws KalahBoardException;

    Player getWinner();
}