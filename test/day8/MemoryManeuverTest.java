package day8;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MemoryManeuverTest {

    @Test
    public void testExample(){
        String input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
        MemoryManeuver maneuver = new MemoryManeuver(input);
        maneuver.parseTree();
        assertEquals(138, maneuver.getMetadataSum());
    }

    @Test
    public void testExamplePart2(){
        String input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
        MemoryManeuver maneuver = new MemoryManeuver(input);
        assertEquals(66, maneuver.getRootValue());
    }

    @Test
    public void testInput() throws IOException {
        File file = new File("C:\\Users\\whwl\\IdeaProjects\\AdventOfCode2018\\resources\\inputDay8.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine();
        MemoryManeuver maneuver = new MemoryManeuver(line);
        maneuver.parseTree();
        assertEquals(48443, maneuver.getMetadataSum());
    }

    @Test
    public void testInputPart2() throws IOException {
        File file = new File("C:\\Users\\whwl\\IdeaProjects\\AdventOfCode2018\\resources\\inputDay8.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine();
        MemoryManeuver maneuver = new MemoryManeuver(line);
        assertEquals(30063, maneuver.getRootValue());
    }

}