package day6;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChronalCoordinatesTest {
    @Test
    public void testExample() {
        String exampleInput = "1, 1\n" +
                "1, 6\n" +
                "8, 3\n" +
                "3, 4\n" +
                "5, 5\n" +
                "8, 9";
        Set<String> exampleInputSet = Arrays.stream(exampleInput.split("\n")).collect(Collectors.toSet());
        ChronalCoordinates chronalCoordinates = new ChronalCoordinates(exampleInputSet);
        assertEquals(17, chronalCoordinates.getBiggestFiniteArea());
    }

    @Test
    public void testExamplePart2() {
        String exampleInput = "1, 1\n" +
                "1, 6\n" +
                "8, 3\n" +
                "3, 4\n" +
                "5, 5\n" +
                "8, 9";
        Set<String> exampleInputSet = Arrays.stream(exampleInput.split("\n")).collect(Collectors.toSet());
        ChronalCoordinates chronalCoordinates = new ChronalCoordinates(exampleInputSet);
        assertEquals(16, chronalCoordinates.getRegionSize(32));
    }

    @Test
    public void testInput() throws IOException {
        File file = new File("C:\\Users\\whwl\\IdeaProjects\\AdventOfCode2018\\resources\\inputDay6.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        Set<String> inputSet = new HashSet<>();
        while ((line = br.readLine()) != null) {
            inputSet.add(line);
        }
        ChronalCoordinates chronalCoordinates = new ChronalCoordinates(inputSet);
        assertEquals(3420, chronalCoordinates.getBiggestFiniteArea());
    }

    @Test
    public void testInputPart2() throws IOException {
        File file = new File("C:\\Users\\whwl\\IdeaProjects\\AdventOfCode2018\\resources\\inputDay6.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        Set<String> inputSet = new HashSet<>();
        while ((line = br.readLine()) != null) {
            inputSet.add(line);
        }
        ChronalCoordinates chronalCoordinates = new ChronalCoordinates(inputSet);
        assertEquals(46667, chronalCoordinates.getRegionSize(10000));
    }
}