package com.example.kalah.model.player;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Players implementation class
 */
@Component
public class Players {

    private List<Player> playerList;

    /**
     * Setups the game players
     */
    @PostConstruct
    private void setup() {
        playerList = new ArrayList<>(2);
        Player player = new Player();
        player.setId(1);
        playerList.add(player);

        player = new Player();
        player.setId(2);
        playerList.add(player);
    }

    /**
     * Gets a player by id
     * @param playerId the player id
     * @return a player given an id
     */
    public Player getPlayerById(int playerId) {
        return playerList.stream().filter(player -> player.getId() == playerId).collect(Collectors.toList()).get(0);
    }

    /**
     * Gets the first player
     * @return the first player
     */
    public Player getPlayerOne() {
        return playerList.get(0);
    }

    /**
     * Gets the next player given the current
     * @param player the current player
     * @return the next player
     */
    public Player getNext(Player player) {
        if(player.getId() == 1)
            return playerList.get(1);
        else
            return playerList.get(0);
    }

    /**
     * Gets the players
     * @return the players
     */
    public List<Player> getPlayers() {
        return playerList;
    }
}
