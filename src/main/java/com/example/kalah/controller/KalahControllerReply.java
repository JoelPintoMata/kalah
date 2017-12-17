package com.example.kalah.controller;

import com.example.kalah.gameboard.House;
import com.example.kalah.model.player.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class KalahControllerReply {

    private static final Logger LOGGER = LoggerFactory.getLogger(KalahControllerReply.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    @Getter
    private List<House> gameBoard;

    @Getter
    private Player nextPlayer;

    @Getter
    private Player winner;

    @Getter
    private String message;

    /**
     * @param gameBoard
     * @param nextPlayer
     * @param winner
     */
    public KalahControllerReply(List<House> gameBoard, Player nextPlayer, Player winner) {
        this.gameBoard = gameBoard;
        this.nextPlayer = nextPlayer;
        this.winner = winner;
    }

    /**
     *  @param message
     * @param gameBoard
     * @param nextPlayer
     * @param winner
     */
    public KalahControllerReply(String message, List<House> gameBoard, Player nextPlayer, Player winner) {
        this.message = message;
        this.gameBoard = gameBoard;
        this.nextPlayer = nextPlayer;
        this.winner = winner;
    }

    /**
     *
     * @return
     */
    public String toJson() {
        String result;
        try {
            result = mapper.writeValueAsString(this);
        } catch (IOException e) {
            LOGGER.error("Exception with message {} caused by {}", e.getMessage(), e.getCause());
            return "";
        }
        return result;
    }
}
