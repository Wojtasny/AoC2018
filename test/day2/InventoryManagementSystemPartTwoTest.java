package day2;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagementSystemPartTwoTest {

    @Test
    public void testInput() {
        List<String> ids = Arrays.asList("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz");
        InventoryManagementSystemPartTwo inventoryManagementSystemPartTwo = new InventoryManagementSystemPartTwo();
        for (String str : ids) {
            inventoryManagementSystemPartTwo.parseId(str);
        }
        assertEquals("fgij", inventoryManagementSystemPartTwo.getMostCommon());
    }

    @Test
    public void testPuzzle() throws IOException {
        InventoryManagementSystemPartTwo inventoryManagementSystem = new InventoryManagementSystemPartTwo();
        File file = new File("C:\\Users\\whwl\\IdeaProjects\\AdventOfCode2018\\resources\\inputDay2.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            inventoryManagementSystem.parseId(line);
        }
        assertEquals("tzyvunogzariwkpcbdewmjhxi", inventoryManagementSystem.getMostCommon());
    }

}