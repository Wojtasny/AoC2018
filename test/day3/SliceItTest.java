package day3;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SliceItTest {

    @Test
    public void testExample(){
        List<String> inputClaims = Stream.of("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2").collect(Collectors.toList());
        SliceIt sliceItManager = new SliceIt();
        for (String claim :
                inputClaims) {
            sliceItManager.parseClaim(claim);
        }
        assertEquals(4, sliceItManager.getOverlappingCount());
        assertEquals(3, sliceItManager.findIntactID());
    }

    @Test
    public void testInput() throws IOException {
        File file = new File ("C:\\Users\\whwl\\IdeaProjects\\AdventOfCode2018\\resources\\inputDay3.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        SliceIt sliceIt = new SliceIt();
        String line;
        while ((line = br.readLine())!= null){
            sliceIt.parseClaim(line);
        }
        assertEquals(103482, sliceIt.getOverlappingCount());
        assertEquals(686, sliceIt.findIntactID());
    }
}