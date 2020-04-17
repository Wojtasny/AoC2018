package day7;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SumOfPartsManagerTest {

    @Test
    public void testExample() {
        String input = "Step C must be finished before step A can begin.\n" +
                "Step C must be finished before step F can begin.\n" +
                "Step A must be finished before step B can begin.\n" +
                "Step A must be finished before step D can begin.\n" +
                "Step B must be finished before step E can begin.\n" +
                "Step D must be finished before step E can begin.\n" +
                "Step F must be finished before step E can begin.";
        SumOfPartsManager manager = new SumOfPartsManager();
        Arrays.stream(input.split("\n")).forEach(manager::parseInformation);
        assertEquals("CABDFE", manager.getOrder());
    }

    @Test
    public void testExamplePart2() {
        String input = "Step C must be finished before step A can begin.\n" +
                "Step C must be finished before step F can begin.\n" +
                "Step A must be finished before step B can begin.\n" +
                "Step A must be finished before step D can begin.\n" +
                "Step B must be finished before step E can begin.\n" +
                "Step D must be finished before step E can begin.\n" +
                "Step F must be finished before step E can begin.";
        SumOfPartsManager manager = new SumOfPartsManager();
        Arrays.stream(input.split("\n")).forEach(manager::parseInformation);
        assertEquals(258, manager.getTimeWithWorkers(2));
    }

    @Test
    public void testInput() throws IOException {
        File file = new File("C:\\Users\\whwl\\IdeaProjects\\AdventOfCode2018\\resources\\inputDay7.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        SumOfPartsManager manager = new SumOfPartsManager();
        String line;
        while ((line = br.readLine()) != null) {
            manager.parseInformation(line);
        }
        assertEquals("SCLPAMQVUWNHODRTGYKBJEFXZI", manager.getOrder());
    }

    @Test
    public void testInputPart2() throws IOException {
        File file = new File("C:\\Users\\whwl\\IdeaProjects\\AdventOfCode2018\\resources\\inputDay7.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        SumOfPartsManager manager = new SumOfPartsManager();
        String line;
        while ((line = br.readLine()) != null) {
            manager.parseInformation(line);
        }
        assertEquals(0, manager.getTimeWithWorkers(5));
    }
}