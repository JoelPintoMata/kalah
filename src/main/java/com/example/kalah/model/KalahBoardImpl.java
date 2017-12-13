package com.example.kalah.model;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class KalahBoardImpl implements KalahBoard {

    @Override
    public int[][] generate(int difficultyLevel) {
        int[][] board = new int[2][difficultyLevel+1];

        for(int i=0; i<2; i++) {
            for(int j=1; j< board[0].length; j++) {
                board[i][j] = 4;
            }
        }
        return board;
    }

    @Override
    public String isValid(String line, String column) {
        return null;
    }

    @Override
    public void play(String line, String column, String value) {

    }

    @Override
    public boolean isGameWon() {
        return false;
    }
}
