package com.example.kalah.controller;

import com.example.kalah.Kalah;
import com.example.kalah.model.board.Board;
import com.example.kalah.model.board.BoardException;
import com.example.kalah.model.house.House;
import com.example.kalah.strategy.StrategyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class KalahController {

    @Autowired
    private Kalah kalah;

    @Autowired
    private Board board;

    @RequestMapping(method = RequestMethod.GET, path="/setup")
    public String setupDefaultLeveStrategy(
            Model model) {
        kalah.setup(null, null);
        loadModel(model, kalah);

        return "play";
    }

    @RequestMapping(method = RequestMethod.GET, path="/setup/level/{level}")
    public String setupDefaultStrategy(
            @PathVariable("level") int level,
            Model model) {
        kalah.setup(level, null);
        loadModel(model, kalah);

        return "play";
    }

    @RequestMapping(method = RequestMethod.GET, path="/setup/level/{level}/strategy/{strategy}")
    public String setup(
            @PathVariable("level") int level,
            @PathVariable("strategy") String strategy,
            Model model) {
        kalah.setup(level, strategy);
        loadModel(model, kalah);

        return "play";
    }

    @RequestMapping(method = RequestMethod.GET, path="/play/player/{playerId}/position/{position}")
    public String play(
            @PathVariable("playerId") int playerId,
            @PathVariable("position") int position,
            Model model) {

        boolean isLoadModel = true;
        try {
            kalah.play(playerId, position);

        } catch (BoardException e) {
            model.addAttribute("message", e.getMessage());
            isLoadModel = false;
        } catch (StrategyException e) {
            model.addAttribute("message", e.getMessage());
        }
        if(isLoadModel)
            loadModel(model, kalah);

        return "play";
    }

    /**
     * Loads the model with the kalah game data
     * @param model the view model
     * @param kalah the kalah game data
     */
    private void loadModel(Model model, Kalah kalah) {
        List<House> boardHouses = kalah.getBoardHouses();

        model.addAttribute("boardHouses", boardHouses);
        model.addAttribute("reversedBoardHouses", reserveList(boardHouses));
        model.addAttribute("player", kalah.getNextPlayer());
        model.addAttribute("winner", kalah.getWinPlayer());
    }

    /**
     * Reverses a list
     * @param list the list to reverse
     * @return the reversed list
     */
    private List<House> reserveList(List<House> list) {
        List<House> resersedBoardHouses = list.stream().map(house -> new House(house.getId(), house.getHouseType(), house.getSeeds(), house.getPlayer()))
                .collect(Collectors.toList());
        Collections.reverse(resersedBoardHouses);
        return resersedBoardHouses;
    }
}