package day5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlchemicalReduction {
    public AlchemicalReduction() {
    }

    int processPolymer(String pol) {
        List<Integer> integerList;
        integerList = pol.chars().boxed().collect(Collectors.toList());
        List<Integer> reducedIntegerList = new ArrayList<>(integerList);

        do {
            integerList = new ArrayList<>(reducedIntegerList);
            reducedIntegerList = reduce(integerList);
        } while (integerList.size() != reducedIntegerList.size());

        return integerList.stream().mapToInt(Integer::intValue) //.map(i -> Character.toChars(i)[0]) //.map(String::valueOf).peek(System.out::println).collect(Collectors.joining());
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append).toString().length();
    }

    int improve(String polymer) {
        int dictionary = 65;
        List<Integer> polymerLengthAfterImprovement = new ArrayList<>();
        for (int i = 0; i <= 25; i++) {
            char toRemove = (char) dictionary;
            String stringToRemove = String.valueOf(toRemove);
            String polymerCopy = new String(polymer);
            polymerCopy = polymerCopy.replaceAll(stringToRemove, "");
            polymerCopy = polymerCopy.replaceAll(stringToRemove.toLowerCase(), "");
            dictionary++;
            polymerLengthAfterImprovement.add(processPolymer(polymerCopy));
        }
        return polymerLengthAfterImprovement.stream().mapToInt(Integer::intValue).min().getAsInt();
    }

    private List<Integer> reduce(List<Integer> integerList) {
        List<Integer> listToReduce = new ArrayList<>(integerList);
        for (int i = 0; i < integerList.size() - 2; i++) {
            int x = listToReduce.get(i);
            int y = listToReduce.get(i + 1);
            if (Math.abs(x - y) == 32) {
                listToReduce.remove(i);
                listToReduce.remove(i);
                break;
            }
        }
        return listToReduce;
    }
}
