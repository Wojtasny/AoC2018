package day2;

import java.util.ArrayList;
import java.util.List;

public class InventoryManagementSystemPartTwo {
    private String mostCommon = "";

    private List<String> idList = new ArrayList<>();

    public void parseId(String id) {
        if (idList.isEmpty()) {
            idList.add(id);
            return;
        }

        String mostSimilar = findSimilar(id);
        if (mostCommon.length() < mostSimilar.length()) {
            mostCommon = mostSimilar;
        }
        idList.add(id);
    }

    private String findSimilar(String id) {
        StringBuilder currentMostSimilar = new StringBuilder("");
        int length = 0;
        for (String str :
                idList) {
            StringBuilder similarity = new StringBuilder();
            length = Math.min(id.length(), str.length());
            for (int i = 0; i < length; i++) {
                if (id.charAt(i) == str.charAt(i)) {
                    similarity.append(id.charAt(i));
                }
            }
            if (similarity.length() > currentMostSimilar.length()) {
                currentMostSimilar = similarity;
            }
        }
        return currentMostSimilar.toString();
    }

    public String getMostCommon() {
        return mostCommon;
    }
}
