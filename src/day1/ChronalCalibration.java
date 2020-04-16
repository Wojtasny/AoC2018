package day1;

public class ChronalCalibration {
    protected int currentFrequency = 0;
    public void adjustFrequency(int freq) {
        currentFrequency += freq;
    }

    public int getCurrentFrequency() {
        return currentFrequency;
    }
}
