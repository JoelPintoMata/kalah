package com.example.kalah.model.board;

import com.example.kalah.KalahApplication;
import com.example.kalah.model.player.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KalahApplication.class)
public class WinningPlayerComparatorTest {

    @Autowired
    private WinningPlayerComparator winningPlayerComparator;

    @Test
    public void compare() {
        Player player1 = new Player();
        player1.setId(1);

        Player player2 = new Player();
        player2.setId(2);

        House house1 = new House(1, HouseType.STORE, 10, player1);
        House house2 = new House(2, HouseType.STORE, 11, player2);

        Assert.assertEquals(winningPlayerComparator.compare(house1, house2), -1);
        Assert.assertEquals(winningPlayerComparator.compare(house2, house1), 1);
        Assert.assertEquals(winningPlayerComparator.compare(house1, house1), 0);
    }
}