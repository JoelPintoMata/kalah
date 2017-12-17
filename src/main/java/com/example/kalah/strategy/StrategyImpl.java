package com.example.kalah.strategy;

import com.example.kalah.gameboard.House;
import com.example.kalah.gameboard.HouseType;
import com.example.kalah.gameboard.Board;
import com.example.kalah.gameboard.BoardException;
import com.example.kalah.model.player.Player;
import com.example.kalah.model.player.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StrategyImpl implements Strategy {

    @Autowired
    private Board board;

    @Autowired
    private Players players;

    private static final int SEEDS_PER_PLAYER = 4;

    private boolean gameWon = false;

    private Player winner;
    private Player nextPlayer;

    @Override
    public void setup(int level) {
        board.setupBoard(level, SEEDS_PER_PLAYER);
        nextPlayer = players.getPlayerOne();
    }

    @Override
    public List<House> getBoard() {
        return board.getHouses();
    }

    @Override
    public Player getFirstPlayer() {
//        default rule
        return getBoard().get(0).getPlayer();
    }

    @Override
    public void play(int playerId, int position) throws BoardException {

//        player list index is zero based
        Player player = players.getPlayerById(playerId);

        isPlayValid(player, position);

        playAux(player, position);
    }

    @Override
    public Player getWinner() {
        return null;
    }

    /**
     * Executes a play
     * @param player the player
     * @param position the position
     * @throws BoardException
     */
    private void playAux(Player player, int position) {
        List<House> boardHouses = this.board.getHouses();

        int numberOfStones = boardHouses.get(position).getSeeds();
        boardHouses.get(position).setSeeds(0);

        int positionAux = ++position;
        do {
            if (positionAux > boardHouses.size()-1) {
//                the end of the list is reached, go to the beginning
                positionAux = 0;
            }
            boardHouses.get(positionAux).setSeeds(boardHouses.get(positionAux).getSeeds() + 1);
            if (isGameWon(boardHouses, player)) {
//                the game is won, nothing else to do here
                winner = player;
                nextPlayer = null;
                return;
            }
            if (--numberOfStones == 0) {
                if (boardHouses.get(positionAux).getPlayer().equals(player) &&
                        boardHouses.get(positionAux).getSeeds() == 0) {
//                    capture the current seed plus any seeds the opponent has on the opposed house
                    captureCurrentAndOponentSeeds(board, positionAux);
//                    no more stones to play
                    nextPlayer = players.getNext(player);
                    return;
                }

                if (boardHouses.get(positionAux).getHouseType().equals(HouseType.STORE)) {
//                    the current player wins one more play
                    nextPlayer = player;
                    return;
                }

//                no more stones to play
                nextPlayer = players.getNext(player);
                return;
            }
            positionAux++;
        } while (true);
    }

    /**
     *  @param board
     * @param positionAux
     */
    private void captureCurrentAndOponentSeeds(Board board, int positionAux) {
        Player player = board.getHouses().get(positionAux).getPlayer();
        board.setCapturedSeeds(player, 1 + board.getOpponentSeeds(positionAux));
    }

    @Override
    public Player getNextPlayer() {
        return this.nextPlayer;
    }

    /**
     * Validates this play
     * @param player the player
     * @param position the position
     * @throws BoardException if the play is invalid
     */
    private void isPlayValid(Player player, int position) throws BoardException {
        List<House> board = this.board.getHouses();

        if(player != nextPlayer)
            throw new BoardException("Un-expected player");

        if(player != board.get(position).getPlayer())
                throw new BoardException("The player can only play on his side of the board");

        if(board.get(position).getHouseType().equals(HouseType.STORE))
            throw new BoardException("The player stores cannot be chose");
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
    private boolean isGameWon(List<House> board, Player player) {
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
