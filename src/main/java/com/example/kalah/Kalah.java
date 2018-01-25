package com.example.kalah;


import com.example.kalah.model.board.BoardException;
import com.example.kalah.model.house.House;
import com.example.kalah.model.player.Player;
import com.example.kalah.strategy.Strategy;
import com.example.kalah.strategy.StrategyException;
import com.example.kalah.strategy.StrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

/**
 * Kalah game implementation class
 */
@Service
@SessionScope
public class Kalah {

    private final int DEFAULT_LEVEL = 6;

    @Autowired
    private StrategyFactory strategyFactory;

    private Strategy strategy;

    /**
     * Setup a Kalah game
     * @param level the game level
     * @param strategyName the strategy name to be applied
     */
    public void setup(Integer level, String strategyName) {
        strategy = strategyFactory.get(strategyName);

        if(level == null)
            strategy.setup(DEFAULT_LEVEL);
        else
            strategy.setup(level);
    }

    /**
     * Gets this strategy board list of houses
     * @return this strategy board list of houses
     */
    public List<House> getBoardHouses() {
        return strategy.getBoardHouses();
    }

    /**
     * Gets the next player
     * @return the next player
     */
    public Player getNextPlayer() {
        return strategy.getNextPlayer();
    }

    /**
     * Performs a play on a board following a specific strategy
     * @param playerId the player id
     * @param position the start position
     * @throws BoardException if the board is invalid
     * @throws StrategyException if the play is invalid
     */
    public void play(int playerId, int position) throws BoardException, StrategyException {
        if(strategy == null)
            throw new BoardException("No strategy found, was this game initialized?");
        strategy.play(playerId, position);
    }


    /**
     * Gets the winning player
     * @return the winning player
     */
    public Player getWinPlayer() {
        return strategy.getWinPlayer();
    }
}
