package com.example.kalah.strategy;

import com.example.kalah.comparator.WinningPlayerComparator;
import com.example.kalah.model.board.Board;
import com.example.kalah.model.house.House;
import com.example.kalah.model.house.HouseType;
import com.example.kalah.model.player.Player;
import com.example.kalah.model.player.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Concrete implementation of a kalah strategy
 * This implementation follows the default rules at [Kalah default rules](https://en.wikipedia.org/wiki/Kalah)
 */
@Component
@SessionScope
public class StrategyImpl implements Strategy {

    @Autowired
    private Board board;

    @Autowired
    private Players players;

    @Autowired
    private WinningPlayerComparator winningPlayerComparator;

//    the number of initial seeds per player
    private static final int SEEDS_PER_PLAYER = 6;

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
    public void play(int playerId, int position) throws StrategyException {
//        player list index is zero based
        currentPlayer = players.getPlayerById(playerId);

        isPlayValid(position);

        playAux(position);
    }

    @Override
    public Player getNextPlayer() {
        if(this.nextPlayer == null)
//            default rule
            this.nextPlayer = getBoardHouses().get(0).getPlayer();
        return this.nextPlayer;
    }

    @Override
    public Player getWinPlayer() {
        return this.winner;
    }

    /**
     * Executes a play
     * @param position the current position
     */
    private void playAux(int position) {
        List<House> boardHouses = this.board.getHouses();
        House currentHouse = boardHouses.get(position);

        int numberOfStones = currentHouse.getSeeds();
        currentHouse.setSeeds(0);
        position++;

        boolean isPlayFinished = false;
        do {
            if (numberOfStones == 1) {
                processPlayLastSeed(position);
                isPlayFinished = true;
//            we cannot play on the opponent store
            } else if (!(boardHouses.get(position).getHouseType().equals(HouseType.STORE)
                    && boardHouses.get(position).getPlayer() != currentPlayer)) {
                incSeeds(position, 1);
                --numberOfStones;
            }
            if(isGameWon()) {
                processGameWin();
            } else if(!isPlayFinished)
                position = nextPosition(position);
        } while (!isPlayFinished);
    }

    /**
     * Processes the win/finish of the game
     */
    private void processGameWin() {
        captureAllSeeds(players.getNext(currentPlayer));
        winner = winningPlayer();
        nextPlayer = null;
    }

    /**
     * Processes the play of the last seed
     * @param position the current position
     */
    private void processPlayLastSeed(int position) {
        List<House> boardHouses = this.board.getHouses();
        if (boardHouses.get(position).getHouseType().equals(HouseType.STORE)) {
//                        the current player wins one more play
            nextPlayer = currentPlayer;

            incSeeds(position, 1);
        } else if (boardHouses.get(position).getPlayer().equals(currentPlayer) &&
                boardHouses.get(position).getSeeds() == 0) {
//                    capture the current seed plus any seeds the opponent has on the opposed house
            captureOponentSeeds(position);
            captureSeeds(1);

//                    no more stones to play
            nextPlayer = players.getNext(currentPlayer);
        } else {
            incSeeds(position, 1);
            nextPlayer = players.getNext(currentPlayer);
        }
    }

    /**
     * Captures all player seeds
     * @param player the player to capture the seeds
     */
    private void captureAllSeeds(Player player) {
        this.board.getHouses()
                .stream()
                .filter(x -> x.getPlayer().getId() == player.getId()
                    && x.getHouseType().equals(HouseType.HOUSE))
                .forEach(x -> {
                    board.getStore(player).addSeeds(x.getSeeds());
                    x.setSeeds(0);
                });
    }

    /**
     * Increments the number of seeds in a house
     * @param position the current position
     * @param seeds the number of seeds to increment
     */
    private void incSeeds(int position, int seeds) {
        House house = board.getHouses().get(position);
        house.addSeeds(seeds);
    }

    /**
     * Gets this game winning player, that is, the player with more seeds in his house
     * @return this game winning player
     */
    private Player winningPlayer() {
        return players.getPlayers()
                .stream()
                .map(player ->board.getStore(player)).collect(Collectors.toList())
                .stream()
                .max(winningPlayerComparator::compare).get().getPlayer();
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
     * @param position the current position
     */
    private void captureOponentSeeds(int position) {
        int opponentPosition = (board.getHouses().size() - 1) - position;

        board.captureSeeds(currentPlayer, board.getHouses().get(opponentPosition).getSeeds());

//        reset the number of seeds the opponent position
        board.getHouses().get(opponentPosition).setSeeds(0);
    }

    /**
     * Captures a single seed
     * @param seeds the number of seeds to capture
     */
    private void captureSeeds(int seeds) {
        board.captureSeeds(currentPlayer, seeds);
    }

    /**
     * Validates this play
     * @param position the current position
     * @throws StrategyException if the play is invalid
     */
    private void isPlayValid(int position) throws StrategyException {

        if(currentPlayer != nextPlayer)
            throw new StrategyException("Un-expected player");

        List<House> board = this.board.getHouses();
        if(currentPlayer != board.get(position).getPlayer())
            throw new StrategyException("The player can only play on his side of the board");

        if(board.get(position).getHouseType().equals(HouseType.STORE))
            throw new StrategyException("The player stores cannot be played");

        if(board.get(position).getSeeds() == 0)
            throw new StrategyException("Houses without seeds cannot be played");
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
