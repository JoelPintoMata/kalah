package com.example.kalah.model.board;

import com.example.kalah.Main;
import com.example.kalah.model.player.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class BoardImplTest {

    @Autowired
    private Board board;

    @Test
    public void setup() {
        board.setup(6, 4);

        Assert.assertNotNull(board.getHouses());
        Assert.assertEquals(board.getHouses().size(), 14);

        Player player1 = new Player();
        player1.setId(1);
        Assert.assertNotNull(board.getStore(player1));

        Player player2 = new Player();
        player2.setId(2);
        Assert.assertNotNull(board.getStore(player2));
    }

    @Test
    public void getHouses() {
        Assert.assertNotNull(board.getHouses());
        Assert.assertEquals(board.getHouses().size(), 14);
    }

    @Test
    public void getStore() {
        Player player1 = new Player();
        player1.setId(1);
        Assert.assertNotNull(board.getStore(player1));

        Player player2 = new Player();
        player2.setId(2);
        Assert.assertNotNull(board.getStore(player2));
    }

    @Test
    public void captureSeeds() {
        Player player1 = new Player();
        player1.setId(1);
        board.captureSeeds(player1, 10);

        Assert.assertEquals(board.getStore(player1).getSeeds(), 10);
    }
}