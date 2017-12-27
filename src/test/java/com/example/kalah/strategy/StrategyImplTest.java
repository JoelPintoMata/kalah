package com.example.kalah.strategy;

import com.example.kalah.Main;
import com.example.kalah.model.house.House;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
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
    public void play() throws StrategyException{

//        capture rules work
        strategy.setup(6);
        List<House> houseList = strategy.getBoardHouses();
        houseList.get(3).setSeeds(1);
        houseList.get(4).setSeeds(0);
        houseList.get(5).setSeeds(3);
        houseList.get(6).setSeeds(4);
        houseList.get(7).setSeeds(0);
        houseList.get(8).setSeeds(13);
        houseList.get(9).setSeeds(10);
        houseList.get(10).setSeeds(9);
        houseList.get(11).setSeeds(9);
        houseList.get(12).setSeeds(8);
        houseList.get(13).setSeeds(1);
        strategy.play(houseList.get(0).getPlayer().getId(), 3);

        Assert.assertEquals(houseList.get(4).getSeeds(), 0);
        Assert.assertEquals(houseList.get(9).getSeeds(), 0);

        Assert.assertEquals(houseList.get(6).getSeeds(), 15);
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