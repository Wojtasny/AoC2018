package day1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChronalCalibrationAdvTest extends ChronalCalibrationTest {

    @Test
    public void testRepeatedFrequency() {
        int[] testFreq1 = {-1, 1};
        int[] testFreq2 = {+3, +3, +4, -2, -4};
        int[] testFreq3 = {-6, +3, +8, +5, -6};
        int[] testFreq4 = {+7, +7, -2, -7, -4};

        assertEquals(0, calculateRepeatedFrequency(testFreq1));
        assertEquals(10, calculateRepeatedFrequency(testFreq2));
        assertEquals(5, calculateRepeatedFrequency(testFreq3));
        assertEquals(14, calculateRepeatedFrequency(testFreq4));
    }

    @Test
    public void testPuzzleInputRepeatedFrequency(){
        int[] inputFreq = frequencyList.stream().mapToInt(i -> i).toArray();
        assertEquals(72330, calculateRepeatedFrequency(inputFreq));
    }

    private int calculateRepeatedFrequency(int[] testFreq) {
        ChronalCalibrationAdv chronalCalibrationAdv = new ChronalCalibrationAdv();
        while (!chronalCalibrationAdv.isFrequencyRepeated()) {
            for (Integer freq: testFreq) {
                chronalCalibrationAdv.adjustFrequency(freq);
                if (chronalCalibrationAdv.isFrequencyRepeated()){
                    break;
                }
            }
        }
        return chronalCalibrationAdv.getRepeatedFrequency();
    }

}