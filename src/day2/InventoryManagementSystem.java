package day2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InventoryManagementSystem {
    private Set<BoxIDDistribution> boxIDs = new HashSet<>();

    private class BoxIDDistribution {
        private Map<Character, Long> distribution;

        public BoxIDDistribution(String boxID) {
            this.distribution = createDistribution(boxID);
        }

        private Map<Character, Long> createDistribution(String boxID) {
            return boxID.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        }
    }

    public void parseID(String str) {
        boxIDs.add(new BoxIDDistribution(str));
    }

    public Long countChecksum() {
        long twos = boxIDs.stream().filter(boxIDDistribution -> boxIDDistribution.distribution.containsValue(2L)).count();
        long threes = boxIDs.stream().filter(boxIDDistribution -> boxIDDistribution.distribution.containsValue(3L)).count();
        return twos * threes;
    }
}
