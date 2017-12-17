package com.example.kalah.gameboard;


import com.example.kalah.model.player.Player;
import com.example.kalah.strategy.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Kalah {

    @Autowired
    private StrategyFactory strategyFactory;

    private int level;
    private Strategy strategy;

    public void setup(int level, String strategyName) {
        this.level = level;

        strategy = strategyFactory.get(strategyName);

        strategy.setup(level);
    }

    public List<House> getBoard() {
        return strategy.getBoard();
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
