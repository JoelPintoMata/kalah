package com.example.kalah.board;


import com.example.kalah.model.board.BoardException;
import com.example.kalah.model.house.House;
import com.example.kalah.model.player.Player;
import com.example.kalah.strategy.Strategy;
import com.example.kalah.strategy.StrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Kalah game implementation class
 */
@Service
public class Kalah {

    private final int DEFAULT_LEVEL = 6;

    @Autowired
    private StrategyFactory strategyFactory;

    private Strategy strategy;

    /**
     *
     * @param level
     * @param strategyName
     */
    public void setup(Integer level, String strategyName) {
        strategy = strategyFactory.get(strategyName);

        if(level == null)
            strategy.setup(DEFAULT_LEVEL);
        else
            strategy.setup(level);
    }

    public List<House> getBoardHouses() {
        return strategy.getBoardHouses();
    }

    public Player getNextPlayer() {
        return strategy.getNextPlayer();
    }

    public Player play(int playerId, int position) throws BoardException {
        strategy.play(playerId, position);
        return strategy.getNextPlayer();
    }

    public Player getWinner() {
        return strategy.getWinner();
    }

    public Player getFirstPlayer() {
        return strategy.getFirstPlayer();
    }
}
