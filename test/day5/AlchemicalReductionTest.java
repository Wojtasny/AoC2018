package day5;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlchemicalReductionTest {
    @Test
    public void testExample() {
        String polymerToAnalyze = "dabAcCaCBAcCcaDA";
        AlchemicalReduction alchemicalReduction = new AlchemicalReduction();
        assertEquals(10, alchemicalReduction.processPolymer(polymerToAnalyze));
    }

    @Test
    public void testExamplePart2() {
        String polymerToAnalyze = "dabAcCaCBAcCcaDA";
        AlchemicalReduction alchemicalReduction = new AlchemicalReduction();
        assertEquals(4, alchemicalReduction.improve(polymerToAnalyze));
    }

    @Test
    public void testInput() throws IOException {
        File file = new File("C:\\Users\\whwl\\IdeaProjects\\AdventOfCode2018\\resources\\inputDay5.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine();
        AlchemicalReduction alchemicalReduction = new AlchemicalReduction();
        assertEquals(9370, alchemicalReduction.processPolymer(line));
    }

    @Test
    public void testInputPart2() throws IOException {
        File file = new File("C:\\Users\\whwl\\IdeaProjects\\AdventOfCode2018\\resources\\inputDay5.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String polymer = br.readLine();
        AlchemicalReduction alchemicalReduction = new AlchemicalReduction();
        assertEquals(0, alchemicalReduction.improve(polymer));
    }
}