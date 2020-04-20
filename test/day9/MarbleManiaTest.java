package day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarbleManiaTest {

    @Test
    public void circularListTestExample1() {
        int players = 10;
        int lastMarbleWorth = 1618;
        MarbleMania mania = new MarbleMania();
        assertEquals(8317, mania.playGame(players, lastMarbleWorth));
    }

    @Test
    public void circularListTestExample2() {
        int players = 13;
        int lastMarbleWorth = 7999;
        MarbleMania mania = new MarbleMania();
        assertEquals(146373, mania.playGame(players, lastMarbleWorth));
    }

    @Test
    public void circularListTestExample3() {
        int players = 17;
        int lastMarbleWorth = 1104;
        MarbleMania mania = new MarbleMania();
        assertEquals(2764, mania.playGame(players, lastMarbleWorth));
    }

    @Test
    public void circularListTestExample4() {
        int players = 21;
        int lastMarbleWorth = 6111;
        MarbleMania mania = new MarbleMania();
        assertEquals(54718, mania.playGame(players, lastMarbleWorth));
    }

    @Test
    public void circularListTestExample5() {
        int players = 30;
        int lastMarbleWorth = 5807;
        MarbleMania mania = new MarbleMania();
        assertEquals(37305, mania.playGame(players, lastMarbleWorth));
    }

    @Test
    public void testInput() {
        int players = 424;
        int lastMarbleWorth = 71144;
        MarbleMania mania = new MarbleMania();
        assertEquals(405143, mania.playGame(players, lastMarbleWorth));
    }

    @Test
    public void testInputPart2() {
        int players = 424;
        int lastMarbleWorth = 7114400;
        MarbleMania mania = new MarbleMania();
        assertEquals(3411514667L, mania.playGame(players, lastMarbleWorth));
    }
}