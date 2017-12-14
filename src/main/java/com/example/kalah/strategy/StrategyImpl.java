package com.example.kalah.strategy;

import java.util.List;

public class StrategyImpl implements Strategy {

    @Override
    public int doOperation(int num1, int num2) {
        return 0;
    }

    @Override
    public List<Integer> play(List<Integer> board, int playerId, int position) {
        int numberOfStones = board.get(position);
        board.set(position, 0);

//        go trough the line
        int positionAux = position;
        do {
            positionAux++;
            if(positionAux > board.size())
                positionAux = 0;
            if(numberOfStones == 0)
//                no more stones to play
                break;
            for(int i=positionAux; i<board.size(); i++) {
//                can the player use this house?
                if (isHouseValid(playerId, positionAux)) {
                    board.set(positionAux, board.get(i) + 1);
                    if (--numberOfStones == 0)
//                    no more stones to play
                    return board;
                }
                positionAux++;
            }

        } while (true);

        return board;
    }

    /**
     * Check if this player can play on this house
     * @param playerId the player id
     * @param positionAux
     * @return
     */
    private boolean isHouseValid(int playerId, int positionAux) {
    }

    /**
     *
     * @param column
     * @return
     */
    private int swithColumn(int column) {
        int result;
        if(column == 0)
            result = 1;
        else
            result = 0;
        return result;
    }
}
