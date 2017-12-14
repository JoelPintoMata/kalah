package com.example.kalah.gameboard;

import com.example.kalah.model.player.Player;
import com.example.kalah.model.player.PlayerImpl;
import com.example.kalah.strategy.Strategy;
import com.example.kalah.strategy.StrategyImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class KalahBoardImpl implements KalahBoard {

    private Strategy strategy;
    private List<Player> playerList = new ArrayList(2);

//    the board game
    private List<Integer> board;

    @Override
    public List<Integer> setup(int level, String strategy) {
        board = new LinkedList<>();
        for(int i=0; i<level*2; i++) {
            board.add(4);
        }
//        initialize stores
        board.set(board.size()/2 - 1, 0);
        board.set(board.size() - 1, 0);

        setPlayers();
        setStrategy(strategy);
        return board;
    }

    @Override
    public String isValid(int lposition) {
        return null;
    }

    @Override
    public List<Integer> play(int playerId, int position) throws KalahBoardException {
        validate(board, playerId, position);
        return strategy.play(board, playerId, position);
    }

    /**
     * Validates this play parameters
     * @param board
     * @param playerId
     * @param position start position
     */
    private void validate(List board, int playerId, int position) throws KalahBoardException {
        if(playerId == 1)
            if(position > board.size()/2)
                throw new KalahBoardException("The player can only play on his side of the board");
        if(playerId == 2)
            if(position < board.size()/2)
                throw new KalahBoardException("The player can only play on his side of the board");
    }

    @Override
    public boolean isGameWon() {
        return false;
    }

    /**
     * Sets this game players
     */
    private void setPlayers() {
        Player player = new PlayerImpl();
        player.setId(1);
        playerList.add(player);

        player = new PlayerImpl();
        player.setId(2);
        playerList.add(player);
    }

    /**
     * Sets this game strategy (rules)
     * @param strategy the strategy definition
     */
    public void setStrategy(String strategy) {
        if(strategy == null || strategy.isEmpty())
            this.strategy = new StrategyImpl();
//        we dont have other strategies at this moment
        else
            this.strategy = new StrategyImpl();
    }
}
