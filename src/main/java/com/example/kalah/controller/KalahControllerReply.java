package com.example.kalah.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@Data
public class KalahControllerReply {

    private static final Logger LOGGER = LoggerFactory.getLogger(KalahControllerReply.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    private List<Integer> gameBoard;

    /**
     *
     * @param gameBoard
     */
    public KalahControllerReply(List<Integer> gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     *
     * @param message the message to return
     */
    public KalahControllerReply(String message) {
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
