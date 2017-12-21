package com.example.kalah.model.player;

import com.example.kalah.KalahApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KalahApplication.class)
public class PlayersTest {

    @Autowired
    private Players players;

    @Test
    public void getPlayerById() {
        Assert.assertNotNull(players.getPlayerById(1));
        Assert.assertNotNull(players.getPlayerById(2));
    }

    @Test
    public void getPlayerOne() {
        Assert.assertNotNull(players.getPlayerById(1));
    }

    @Test
    public void getNext() {
        Assert.assertEquals(players.getNext(players.getPlayerById(1)), players.getPlayerById(2));
    }

    @Test
    public void getPlayers() {
        Assert.assertNotNull(players.getPlayers());
        Assert.assertEquals(players.getPlayers().get(0).getId(), 1);
        Assert.assertEquals(players.getPlayers().get(1).getId(), 2);
        Assert.assertEquals(players.getPlayers().size(), 2);
    }
}