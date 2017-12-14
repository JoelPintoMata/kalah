package com.example.kalah.strategy;

import com.example.kalah.gameboard.KalahBoard;
import com.example.kalah.gameboard.KalahBoardException;
import com.example.kalah.gameboard.KalahBoardImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

class StrategyImplTest {

    @Test
    void doOperation() {
    }

    @Test
    void play_1() throws KalahBoardException {
        KalahBoard kalahBoard = new KalahBoardImpl();
        List<Integer> gameBoard = kalahBoard.setup(6, null);
        gameBoard = kalahBoard.play(1, 3);

        Assert.assertEquals("Initial house must be zero", gameBoard.get(3).intValue(), 0);

//        check players 2 store
        Assert.assertEquals("Opponent`s store must must be affected", gameBoard.get(gameBoard.size()-1).intValue(), 0);
    }
}