package day2;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagementSystemTest {
    @Test
    public void checkExample() {
        List<String> ids = Arrays.asList("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab");
        InventoryManagementSystem ims = new InventoryManagementSystem();
        ids.forEach(ims::parseID);
        assertEquals(12, ims.countChecksum());
    }

    @Test
    public void checkPuzzle() throws IOException {
        InventoryManagementSystem inventoryManagementSystem = new InventoryManagementSystem();
        File file = new File("C:\\Users\\whwl\\IdeaProjects\\AdventOfCode2018\\resources\\inputDay2.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String string;
        while ((string = br.readLine()) != null) {
            inventoryManagementSystem.parseID(string);
        }
        assertEquals(5750, inventoryManagementSystem.countChecksum());
    }

}