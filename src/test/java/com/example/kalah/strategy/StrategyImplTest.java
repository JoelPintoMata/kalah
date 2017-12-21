package com.example.kalah.strategy;

import com.example.kalah.KalahApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KalahApplication.class)
public class StrategyImplTest {

    @Autowired
    private Strategy strategy;

    @Test
    public void setup() {
        strategy.setup(6);
        Assert.assertNotNull(strategy.getBoardHouses());
        Assert.assertNotNull(strategy.getNextPlayer());
    }

    @Test
    public void getBoardHouses() {
        strategy.setup(6);
        Assert.assertNotNull(strategy.getBoardHouses());
    }

    @Test
    public void play() {
    }

    @Test
    public void getFirstPlayer() {
        strategy.setup(6);
        Assert.assertNotNull(strategy.getFirstPlayer());
    }

    @Test
    public void getNextPlayer() {
        strategy.setup(6);
        Assert.assertNotNull(strategy.getNextPlayer());
    }

    @Test
    public void getWinner() {
    }
}