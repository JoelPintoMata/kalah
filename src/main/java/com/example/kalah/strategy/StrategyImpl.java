package com.example.kalah.strategy;

import com.example.kalah.model.board.Board;
import com.example.kalah.model.board.BoardException;
import com.example.kalah.model.house.House;
import com.example.kalah.model.player.Player;
import com.example.kalah.model.player.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Concrete implementation of a kalah strategy
 * This implementation follows the default rules at [Kalah default rules](https://en.wikipedia.org/wiki/Kalah)
 */
@Component
public class StrategyImpl implements Strategy {

    @Autowired
    private Board board;

    @Autowired
    private Players players;

    @Autowired
    private WinningPlayerComparator winningPlayerComparator;

//    the number of initial seeds per player
    private static final int SEEDS_PER_PLAYER = 4;

    private Player currentPlayer;
    private Player winner;
    private Player nextPlayer;

    @Override
    public void setup(int level) {
        board.setup(level, SEEDS_PER_PLAYER);
        nextPlayer = players.getPlayerOne();
    }

    @Override
    public List<House> getBoardHouses() {
        return board.getHouses();
    }

    @Override
    public void play(int playerId, int position) throws BoardException {
//        player list index is zero based
        currentPlayer = players.getPlayerById(playerId);

        isPlayValid(position);

        playAux(position);
    }

    @Override
    public Player getFirstPlayer() {
//        default rule
        return getBoardHouses().get(0).getPlayer();
    }

    @Override
    public Player getNextPlayer() {
        return this.nextPlayer;
    }

    @Override
    public Player getWinner() {
        return this.winner;
    }

    /**
     * Executes a play
     * @param position the position
     */
    private void playAux(int position) {
        List<House> boardHouses = this.board.getHouses();
        House currentHouse = boardHouses.get(position);

        int numberOfStones = currentHouse.getSeeds();
        currentHouse.setSeeds(0);
        position++;

        boolean isFinished = false;
        do {
            if (numberOfStones == 1) {
                if (boardHouses.get(position).getHouseType().equals(HouseType.STORE)) {
//                        the current player wins one more play
                    nextPlayer = currentPlayer;

                    incHouseSeeds(position, 1);
                } else if (boardHouses.get(position).getPlayer().equals(currentPlayer) &&
                        boardHouses.get(position).getSeeds() == 0) {
//                    capture the current seed plus any seeds the opponent has on the opposed house
                    captureOponentSeeds(position);
                    captureSeed();

//                    no more stones to play
                    nextPlayer = players.getNext(currentPlayer);
                } else {
                    incHouseSeeds(position, 1);
                    nextPlayer = players.getNext(currentPlayer);
                }
                isFinished = true;
            } else {
                incHouseSeeds(position, 1);
                --numberOfStones;
            }
            if(isGameWon()) {
                winner = winningPlayer();
                nextPlayer = null;
            } else if(!isFinished)
                position = nextPosition(position);
        } while (!isFinished);
    }

    /**
     * Increments the number of seeds in a house
     * @param position the position
     * @param seeds the number of seeds to increment
     */
    private void incHouseSeeds(int position, int seeds) {
        House house = board.getHouses().get(position);
        house.setSeeds(house.getSeeds() + seeds);
    }

    /**
     * Gets this game winning player
     * @return this game winning player
     */
    private Player winningPlayer() {
        List<House> playersStore = players.getPlayers().stream().map(player -> board.getStore(player)).collect(Collectors.toList());
        return playersStore.stream().max(winningPlayerComparator::compare).get().getPlayer();
    }

    /**
     * Calculates the next position
     * @param position the current position
     * @return the next position
     */
    private int nextPosition(int position) {
        if (position == board.getHouses().size()-1) {
//            the end of the list is reached, go to the beginning
            return  0;
        } else {
            return ++position;
        }
    }

    /**
     * Captures the opponent seeds.
     *  1) retrieves the seeds in the opposite position house
     *  2) resets the opposite position house number of seeds
     *  3) adds the opponents seeds to the current position player house
     * @param position the position
     */
    private void captureOponentSeeds(int position) {
        int opponentPosition = (board.getHouses().size() - 1) - position;

        board.captureSeeds(currentPlayer, board.getHouses().get(opponentPosition).getSeeds());

//        reset the number of seeds the opponent position
        board.getHouses().get(opponentPosition).setSeeds(0);
    }

    /**
     * Captures a single seed
     */
    private void captureSeed() {
        board.captureSeeds(currentPlayer, 1);
    }

    /**
     * Validates this play
     * @param position the position
     * @throws BoardException if the play is invalid
     */
    private void isPlayValid(int position) throws BoardException {
        List<House> board = this.board.getHouses();

        if(currentPlayer != nextPlayer)
            throw new BoardException("Un-expected player");

        if(currentPlayer != board.get(position).getPlayer())
            throw new BoardException("The player can only play on his side of the board");

        if(board.get(position).getHouseType().equals(HouseType.STORE))
            throw new BoardException("The player stores cannot be played");
    }

    /**
     * Checks if the game is won
     * @return the id of the wining player or null otherwise
     */
    private boolean isGameWon() {
        return board.getHouses().stream().filter(house -> house.getPlayer().equals(currentPlayer)
                && house.getHouseType().equals(HouseType.HOUSE)
                && house.getSeeds() != 0
        ).collect(Collectors.toList()).isEmpty();
    }
}
