package com.example.kalah.controller;

import com.example.kalah.gameboard.BoardException;
import com.example.kalah.gameboard.House;
import com.example.kalah.gameboard.Kalah;
import com.example.kalah.gameboard.Board;
import com.example.kalah.model.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class KalahController {

    @Autowired
    private Kalah kalah;

    @Autowired
    private Board board;

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

    @RequestMapping("/")
    public String kalah(Map<String, Object> model) {
        model.put("message", this.message);
        return "kalah";
    }

    @RequestMapping(method = RequestMethod.GET, path="/setup/level/{level}")
    public ResponseEntity setupDefaultStrategy(
            @PathVariable("level") int level,
            Map<String, Object> model) {
        kalah.setup(level, null);

        List<House> gameBoard = kalah.getBoard();
        Player firstPlayer = kalah.getFirstPlayer();

        return accepted(gameBoard, firstPlayer, null);
    }

    @RequestMapping(method = RequestMethod.GET, path="/setup/level/{level}/strategy/{strategy}")
    public ResponseEntity setup(
            @PathVariable("level") int level,
            @PathVariable("strategy") String strategy,
            Map<String, Object> model) {
        kalah.setup(level, strategy);

        List<House> gameBoard = kalah.getBoard();
        Player firstPlayer = kalah.getFirstPlayer();

        return accepted(gameBoard, firstPlayer, null);
    }

    @RequestMapping(method = RequestMethod.GET, path="/play/player/{playerId}/position/{position}")
    public ResponseEntity<String> play(
            @PathVariable("playerId") int playerId,
            @PathVariable("position") int position,
            Map<String, Object> model) {

        try {
            kalah.play(playerId, position);
        } catch (BoardException e) {

            List<House> gameBoard = kalah.getBoard();
            Player nextPlayer = kalah.getNextPlayer();
            Player winner = kalah.getWinner();

            return preConditionFailed(e.getMessage(), gameBoard, nextPlayer, winner);
        }

        List<House> gameBoard = kalah.getBoard();
        Player nextPlayer = kalah.getNextPlayer();
        Player winner = kalah.getWinner();

        return accepted(gameBoard, nextPlayer, winner);
    }

    /**
     *
     * @return
     * @param gameBoard
     * @param winner
     */
    private ResponseEntity accepted(List<House> gameBoard, Player nextPlayer, Player winner) {
        KalahControllerReply kalahControllerReply = new KalahControllerReply(gameBoard, nextPlayer, winner);
        return new ResponseEntity(kalahControllerReply.toJson(), HttpStatus.ACCEPTED);
    }

    /**
     * Handles an pre-conditions entity (412) error code
     * @param message
     * @param gameBoard
     * @return Response entity containing a 412 error code
     */
    private ResponseEntity<String> preConditionFailed(String message, List<House> gameBoard, Player nextPlayer, Player winner) {
        KalahControllerReply kalahControllerReply = new KalahControllerReply(message, gameBoard, nextPlayer, winner);
        return new ResponseEntity(kalahControllerReply.toJson(), HttpStatus.PRECONDITION_FAILED);
    }
}