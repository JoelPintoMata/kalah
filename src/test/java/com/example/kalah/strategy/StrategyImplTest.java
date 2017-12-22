package com.example.kalah.strategy;

import com.example.kalah.KalahApplication;
import com.example.kalah.model.board.BoardException;
import com.example.kalah.model.house.House;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


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
    public void play() throws BoardException{

//        capture rules work
        strategy.setup(6);
        List<House> houseList = strategy.getBoardHouses();
        houseList.get(4).setSeeds(0);
        houseList.get(5).setSeeds(5);
        houseList.get(6).setSeeds(1);
        houseList.get(7).setSeeds(0);
        houseList.get(8).setSeeds(6);
        houseList.get(9).setSeeds(5);
        houseList.get(10).setSeeds(5);
        houseList.get(11).setSeeds(5);
        houseList.get(12).setSeeds(5);
        houseList.get(13).setSeeds(0);
        strategy.play(houseList.get(0).getPlayer().getId(), 0);

        Assert.assertEquals(houseList.get(0).getSeeds(), 0);
        Assert.assertEquals(houseList.get(4).getSeeds(), 0);
        Assert.assertEquals(houseList.get(9).getSeeds(), 0);

        Assert.assertEquals(houseList.get(6).getSeeds(), 7);
        strategy.setup(6);
        houseList = strategy.getBoardHouses();
        strategy.play(houseList.get(0).getPlayer().getId(), 0);
        houseList.get(0).setSeeds(0);
        houseList.get(1).setSeeds(7);
        houseList.get(2).setSeeds(5);
        houseList.get(3).setSeeds(5);
        houseList.get(4).setSeeds(5);
        houseList.get(5).setSeeds(4);
        houseList.get(6).setSeeds(0);
        houseList.get(7).setSeeds(4);
        houseList.get(8).setSeeds(4);
        houseList.get(9).setSeeds(4);
        houseList.get(10).setSeeds(4);
        houseList.get(11).setSeeds(0);
        houseList.get(12).setSeeds(5);
        houseList.get(13).setSeeds(1);
        strategy.play(houseList.get(7).getPlayer().getId(), 7);

        Assert.assertEquals(houseList.get(2).getSeeds(), 0);
        Assert.assertEquals(houseList.get(7).getSeeds(), 0);
        Assert.assertEquals(houseList.get(11).getSeeds(), 0);

        Assert.assertEquals(houseList.get(13).getSeeds(), 7);
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