package com.example.kalah.controller;

import com.example.kalah.board.Kalah;
import com.example.kalah.model.board.Board;
import com.example.kalah.model.board.BoardException;
import com.example.kalah.model.board.House;
import com.example.kalah.model.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class KalahController {

    @Autowired
    private Kalah kalah;

    @Autowired
    private Board board;

    @RequestMapping(method = RequestMethod.GET, path="/setup/level/{level}")
    public String setupDefaultStrategy(
            @PathVariable("level") int level,
            Model model) {
        kalah.setup(level, null);

        List<House> boardHouses = kalah.getBoardHouses();
        Player player = kalah.getFirstPlayer();

        model.addAttribute("player", player);
        model.addAttribute("boardHouses", boardHouses);

        return "play";
    }

    @RequestMapping(method = RequestMethod.GET, path="/setup/level/{level}/strategy/{strategy}")
    public String setup(
            @PathVariable("level") int level,
            @PathVariable("strategy") String strategy,
            Model model) {
        kalah.setup(level, strategy);

        List<House> boardHouses = kalah.getBoardHouses();
        Player player = kalah.getFirstPlayer();

        model.addAttribute("boardHouses", boardHouses);
        model.addAttribute("player", player);

        return "play";
    }

    @RequestMapping(method = RequestMethod.GET, path="/play/player/{playerId}/position/{position}")
    public String play(
            @PathVariable("playerId") int playerId,
            @PathVariable("position") int position,
            Model model) {

        try {
            kalah.play(playerId, position);
        } catch (BoardException e) {
            model.addAttribute("message", e.getMessage());
        }

        List<House> boardHouses = kalah.getBoardHouses();
        Player player = kalah.getNextPlayer();
        Player winner = kalah.getWinner();


        model.addAttribute("boardHouses", boardHouses);
        model.addAttribute("player", player);
        model.addAttribute("winner", winner);

        return "play";
    }
}