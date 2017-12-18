package com.example.kalah.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StrategyFactory {

    @Autowired
    private Strategy strategy;

    public Strategy get(String strategy) {
//        if(strategy == null || strategy.isEmpty())
            return this.strategy;
//        we dont have other strategies at this moment
//        else
//            ...
    }
}
