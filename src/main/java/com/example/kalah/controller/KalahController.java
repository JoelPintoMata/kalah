package com.example.kalah.controller;

import com.example.kalah.gameboard.KalahBoard;
import com.example.kalah.gameboard.KalahBoardException;
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
    private KalahBoard kalahBoard;

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
        List<Integer> gameBoard = kalahBoard.setup(level, null);
        return accepted(gameBoard);
    }

    @RequestMapping(method = RequestMethod.GET, path="/setup/level/{level}/strategy/{strategy}")
    public ResponseEntity setup(
            @PathVariable("level") int level,
            @PathVariable("strategy") String strategy,
            Map<String, Object> model) {
        List<Integer> gameBoard = kalahBoard.setup(level, strategy);
        return accepted(gameBoard);
    }

    @RequestMapping(method = RequestMethod.GET, path="/play/player/{playerId}/position/{position}")
    public ResponseEntity play(
            @PathVariable("playerId") int playerId,
            @PathVariable("position") int position,
            Map<String, Object> model) {
        List<Integer> gameBoard = null;
        try {
            gameBoard = kalahBoard.play(playerId, position);
        } catch (KalahBoardException e) {
            return preConditionFailed(e.getMessage());
        }
        return accepted(gameBoard);
    }

    /**
     *
     * @return
     */
    private ResponseEntity<String> accepted() {
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     *
     * @return
     * @param gameBoard
     */
    private ResponseEntity accepted(List<Integer> gameBoard) {
        KalahControllerReply kalahControllerReply = new KalahControllerReply(gameBoard);
        return new ResponseEntity(kalahControllerReply.toJson(), HttpStatus.ACCEPTED);
    }

    /**
     * Handles an pre-conditions entity (412) error code
     * @param message
     * @return Response entity containing a 412 error code
     */
    private ResponseEntity<String> preConditionFailed(String message) {
        KalahControllerReply kalahControllerReply = new KalahControllerReply(message);
        return new ResponseEntity(kalahControllerReply.toJson(), HttpStatus.PRECONDITION_FAILED);
    }
}