package day10;

import day7.SumOfPartsManager;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StarsAlignTest {
    @Test
    public void testPoint(){
        String line = "position=< 9,  1> velocity=< 0,  2>";
        StarsAlign starsAlign = new StarsAlign();
        starsAlign.parsePoint(line);
        StarsAlign.Point point = starsAlign.points.get(0);
        assertEquals(9, point.getPositionX());
        assertEquals(1, point.getPositionY());
        point.nextSecond();
        assertEquals(9, point.getPositionX());
        assertEquals(3, point.getPositionY());
    }

    @Test
    public void testInputExample(){
        String input = "position=< 9,  1> velocity=< 0,  2>\n" +
                "position=< 7,  0> velocity=<-1,  0>\n" +
                "position=< 3, -2> velocity=<-1,  1>\n" +
                "position=< 6, 10> velocity=<-2, -1>\n" +
                "position=< 2, -4> velocity=< 2,  2>\n" +
                "position=<-6, 10> velocity=< 2, -2>\n" +
                "position=< 1,  8> velocity=< 1, -1>\n" +
                "position=< 1,  7> velocity=< 1,  0>\n" +
                "position=<-3, 11> velocity=< 1, -2>\n" +
                "position=< 7,  6> velocity=<-1, -1>\n" +
                "position=<-2,  3> velocity=< 1,  0>\n" +
                "position=<-4,  3> velocity=< 2,  0>\n" +
                "position=<10, -3> velocity=<-1,  1>\n" +
                "position=< 5, 11> velocity=< 1, -2>\n" +
                "position=< 4,  7> velocity=< 0, -1>\n" +
                "position=< 8, -2> velocity=< 0,  1>\n" +
                "position=<15,  0> velocity=<-2,  0>\n" +
                "position=< 1,  6> velocity=< 1,  0>\n" +
                "position=< 8,  9> velocity=< 0, -1>\n" +
                "position=< 3,  3> velocity=<-1,  1>\n" +
                "position=< 0,  5> velocity=< 0, -1>\n" +
                "position=<-2,  2> velocity=< 2,  0>\n" +
                "position=< 5, -2> velocity=< 1,  2>\n" +
                "position=< 1,  4> velocity=< 2,  1>\n" +
                "position=<-2,  7> velocity=< 2, -2>\n" +
                "position=< 3,  6> velocity=<-1, -1>\n" +
                "position=< 5,  0> velocity=< 1,  0>\n" +
                "position=<-6,  0> velocity=< 2,  0>\n" +
                "position=< 5,  9> velocity=< 1, -2>\n" +
                "position=<14,  7> velocity=<-2,  0>\n" +
                "position=<-3,  6> velocity=< 2, -1>";
        StarsAlign starsAlign = new StarsAlign();
        Arrays.stream(input.split("\n")).forEach(starsAlign::parsePoint);
        starsAlign.printStars();
        starsAlign.nextSecond();
        starsAlign.printStars();
        starsAlign.nextSecond();
        starsAlign.printStars();
        starsAlign.nextSecond();
        starsAlign.printStars();
    }

    @Test
    public void testInput() throws IOException {
        File file = new File("C:\\Users\\whwl\\IdeaProjects\\AdventOfCode2018\\resources\\inputDay10.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        StarsAlign starsAlign = new StarsAlign();
        String line;
        while ((line = br.readLine()) != null) {
            starsAlign.parsePoint(line);
        }
        int secondsCounter = 0;
        while(secondsCounter<10888){
            starsAlign.nextSecond();
            ++secondsCounter;
        }
        starsAlign.printStars();
    }
}