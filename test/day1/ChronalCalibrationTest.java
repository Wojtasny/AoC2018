package day1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChronalCalibrationTest {

    protected static ArrayList<Integer> frequencyList = new ArrayList<>();

    @BeforeAll
    static void setup() throws IOException {
        File file = new File("C:\\Users\\whwl\\IdeaProjects\\AdventOfCode2018\\resources\\inputDay1.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String string;
        while ((string = br.readLine()) != null) {
            frequencyList.add(Integer.valueOf(string));
        }
    }

    @Test
    public void testFrequency() {
        ChronalCalibration chronalCalibration = new ChronalCalibration();
        for (Integer integer : frequencyList) {
            chronalCalibration.adjustFrequency(integer);
        }
        assertEquals(505, chronalCalibration.getCurrentFrequency());
    }
}