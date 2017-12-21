package com.example.kalah.model.board;

import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * Compares the house's number of seeds
 */
@Component
public class WinningPlayerComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        House house1 = (House) o1;
        House house2 = (House) o2;
        return house1.getSeeds() - house2.getSeeds();
    }
}
