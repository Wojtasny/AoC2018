package day4;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ReposeRecordTest {
    @Test
    public void testExample() {
        String exampleInput = new String("[1518-11-01 00:00] Guard #10 begins shift\n" +
                "[1518-11-01 00:05] falls asleep\n" +
                "[1518-11-01 00:25] wakes up\n" +
                "[1518-11-01 00:30] falls asleep\n" +
                "[1518-11-01 00:55] wakes up\n" +
                "[1518-11-01 23:58] Guard #99 begins shift\n" +
                "[1518-11-02 00:40] falls asleep\n" +
                "[1518-11-02 00:50] wakes up\n" +
                "[1518-11-03 00:05] Guard #10 begins shift\n" +
                "[1518-11-03 00:24] falls asleep\n" +
                "[1518-11-03 00:29] wakes up\n" +
                "[1518-11-04 00:02] Guard #99 begins shift\n" +
                "[1518-11-04 00:36] falls asleep\n" +
                "[1518-11-04 00:46] wakes up\n" +
                "[1518-11-05 00:03] Guard #99 begins shift\n" +
                "[1518-11-05 00:45] falls asleep\n" +
                "[1518-11-05 00:55] wakes up");
        List<String> inputList = Arrays.stream(exampleInput.split("\n")).collect(Collectors.toList());
        ReposeRecord reposeRecord = new ReposeRecord();
        inputList.forEach(reposeRecord.factoryConsumer::consume);

        assertEquals(240, reposeRecord.getPartOne());
        assertEquals(240, reposeRecord.getPartTwo());
    }
    @Test
    public void testPuzzle() throws IOException {
        File file = new File ("C:\\Users\\whwl\\IdeaProjects\\AdventOfCode2018\\resources\\inputDay4.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        ReposeRecord reposeRecord = new ReposeRecord();
        String line;
        while ((line = br.readLine())!= null){
            reposeRecord.factoryConsumer.consume(line);
        }


        assertEquals(99911, reposeRecord.getPartOne());
        assertEquals(65854, reposeRecord.getPartTwo());
    }

}