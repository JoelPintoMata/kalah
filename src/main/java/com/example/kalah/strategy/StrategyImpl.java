package com.example.kalah.strategy;

import com.example.kalah.gameboard.KalahBoardException;
import com.example.kalah.model.player.Player;
import lombok.Getter;

import java.util.List;

public class StrategyImpl implements Strategy {

    private static final int SEEDS_PER_PLAYER = 4;
    private boolean gameWon = false;

    @Getter
    private Player winner;

    @Override
    public int doOperation(int num1, int num2) {
        return 0;
    }

    @Override
    public List<Integer> play(List<Integer> board, Player player, int position) throws KalahBoardException {
        isPlayValid(board, player, position);

        return performPlay(board, player, position);
    }

    /**
     * Executes a play
     * @param board the game board
     * @param player the player
     * @param position the position
     * @return the game board after the play is executed
     * @throws KalahBoardException
     */
    public List<Integer> performPlay(List<Integer> board, Player player, int position) {
        int numberOfStones = board.get(position);
        board.set(position, 0);

        int positionAux = position;
        do {
            for(int i=++positionAux; i<board.size(); i++) {
                if (positionAux > board.size()) {
//                    the end of the list is reached, go to the beginning
                    positionAux = 0;
                }
                board.set(positionAux, board.get(i) + 1);
                if (isGameWon(board, player)) {
//                    the game is won, nothing else to do here
                    return board;
                }
                if (--numberOfStones == 0) {
//                    no more stones to play
                    return board;
                }
                positionAux++;
            }

        } while (true);
    }

    /**
     * Validates this play
     * @param board the game board
     * @param player the player id
     * @param position the position
     * @throws KalahBoardException if the play is invalid
     */
    private void isPlayValid(List board, Player player, int position) throws KalahBoardException {
        if(player.getId() == 1)
            if(position > board.size()/2)
                throw new KalahBoardException("The player can only play on his side of the board");
        if(player.getId() == 2)
            if(position < board.size()/2)
                throw new KalahBoardException("The player can only play on his side of the board");

        if((position == board.size()/2 - 1)
                || (position == board.size() - 1))
            throw new KalahBoardException("The player stores cannot be chose");
    }

    /**
     * Check if this player can play on this house
     * @param board the game board
     * @param player the player
     * @param position the current position
     * @return a boolean indicating if the player can play in this house
     */
    private boolean isHouseValid(List board, Player player, int position) {
        boolean result = true;
        if(player.getId() == 1)
            if(position == board.size()/2 - 1)
                result = false;
        if(player.getId() == 2)
            if(position == board.size() - 1)
                result = false;
        return result;
    }

    /**
     *
     * @param board the game board
     * @param player the player
     * @return the id of the wining player or null otherwise
     */
    private boolean isGameWon(List<Integer> board, Player player) {
        boolean result = false;
        if(((board.size()/2 - 1) == SEEDS_PER_PLAYER * (board.size()/2 - 1))
                || ((board.size() - 1) == SEEDS_PER_PLAYER * (board.size()/2 - 1))) {
            this.gameWon = true;
            this.winner = player;
            result = true;
        }
        return result;
    }
}
