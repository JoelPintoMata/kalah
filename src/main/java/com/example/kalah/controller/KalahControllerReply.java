package com.example.kalah.controller;

import com.example.kalah.model.player.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class KalahControllerReply {

    private static final Logger LOGGER = LoggerFactory.getLogger(KalahControllerReply.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    @Getter
    private String message;

    @Getter
    private Player winner;

    @Getter
    private List<Integer> gameBoard;

    /**
     *
     * @param message the message to return
     */
    public KalahControllerReply(String message) {
        this.message = message;
    }

    /**
     *
     * @param gameBoard
     */
    public KalahControllerReply(List<Integer> gameBoard) {
        this.gameBoard = gameBoard;
    }

    public KalahControllerReply(List<Integer> gameBoard, Player winner) {
        this.gameBoard = gameBoard;
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
