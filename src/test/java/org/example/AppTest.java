package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class AppTest {
    @BeforeEach
    void setUp() {
        App.addPlayer("peter");
        App.addPlayer("chioma");
    }

    @Test
    void addPlayer() {

        int expectedSize = 2;
        int actualSize = App.players.size();

        String expectedFirstPlayer = "peter";
        String actualFirstPlayer = App.players.get(0);


        assertEquals(expectedSize, actualSize);
        assertEquals(expectedFirstPlayer, actualFirstPlayer);
    }

    @Test
    void bridge() {
        App.diceRoll = Arrays.asList(3, 3);
        App.bridge("chioma");
        int expected = 12;
        int actual = App.positions.get("chioma");

        assertEquals(expected, actual);
    }
}
