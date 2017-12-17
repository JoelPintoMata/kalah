package com.example.kalah.model.player;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Players {

    private List<Player> playerList;

    @PostConstruct
    private void setupPlayers() {
        playerList = new ArrayList<>(2);
        Player player = new Player();
        player.setId(1);
        playerList.add(player);

        player = new Player();
        player.setId(2);
        playerList.add(player);
    }

    public Player getPlayerById(int playerId) {
        return playerList.stream().filter(player -> player.getId() == playerId).collect(Collectors.toList()).get(0);
    }

    public Player getPlayerOne() {
        return playerList.get(0);
    }

    public Player getPlayerTwo() {
        return playerList.get(1);
    }

    public Player getNext(Player player) {
        if(player.getId() == 1)
            return playerList.get(1);
        else
            return playerList.get(0);
    }
}
