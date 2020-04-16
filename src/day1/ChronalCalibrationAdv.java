package day1;

import java.util.HashSet;

public class ChronalCalibrationAdv extends ChronalCalibration {
    private HashSet<Integer> frequencyHistory = new HashSet<>();
    private boolean frequencyRepeated = false;
    private int repeatedFrequency;

    public boolean isFrequencyRepeated() {
        return frequencyRepeated;
    }

    public int getRepeatedFrequency() {
        return repeatedFrequency;
    }

    public void setFrequencyRepeated(boolean frequencyRepeated) {
        this.frequencyRepeated = frequencyRepeated;
    }

    public void setRepeatedFrequency(int repeatedFrequency) {
        this.repeatedFrequency = repeatedFrequency;
        this.setFrequencyRepeated(true);
    }

    @Override
    public void adjustFrequency(int freq) {
        if(!frequencyHistory.add(currentFrequency)){
            setRepeatedFrequency(currentFrequency);
        }
        this.currentFrequency += freq;
    }
}
