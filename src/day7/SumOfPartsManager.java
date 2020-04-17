package day7;

import java.util.*;
import java.util.stream.Collectors;

public class SumOfPartsManager {
    private Set<Parts> partsToAssemble = new HashSet<>();
    private Set<Parts> partsDone = new HashSet<>();

    void parseInformation(String information) {
        String[] informationArray = information.split(" ");
        String dependee = informationArray[7];
        Parts dependeePart = getPartByName(dependee);
        String dependentOnString = informationArray[1];
        Parts dependentOnPart = getPartByName(dependentOnString);
        dependeePart.addDependency(dependentOnPart);
    }

    private Parts getPartByName(String dependeeName) {
        Optional<Parts> dependeePartOptional = partsToAssemble.stream().filter(part -> part.getPartName().equals(dependeeName)).findFirst();
        if (dependeePartOptional.isPresent()) {
            return dependeePartOptional.get();
        } else {
            Parts part = new Parts(dependeeName);
            partsToAssemble.add(part);
            return part;
        }
    }

    String getOrder() {
        StringBuilder orderedPartsString = new StringBuilder();
        while (partsToAssemble.size() != partsDone.size()) {
            TreeSet<Parts> available = partsToAssemble.stream()
                    .filter(part -> part.getDependents().isEmpty() || partsDone.containsAll(part.getDependents()))
                    .filter(parts -> !partsDone.contains(parts))
                    .collect(Collectors.toCollection(TreeSet::new));

            Parts partToAssemble = available.first();
            partsDone.add(partToAssemble);
            orderedPartsString.append(partToAssemble.getPartName());
        }
        return orderedPartsString.toString();
    }

    int getTimeWithWorkers(int amountOfWorkers) {
        int seconds = 0;
        Map<Parts, Integer> workingOnPart = new HashMap<>();
        while (partsToAssemble.size() != partsDone.size()) {
            TreeSet<Parts> available = partsToAssemble.stream()
                    .filter(part -> part.getDependents().isEmpty() || partsDone.containsAll(part.getDependents()))
                    .filter(parts -> !partsDone.contains(parts))
                    .filter(parts -> !workingOnPart.containsKey(parts))
                    .collect(Collectors.toCollection(TreeSet::new));
            int alreadyWorking = workingOnPart.size();
            for (int w = 0; w < amountOfWorkers - alreadyWorking; ++w) {
                Parts part = available.pollFirst();
                if (part != null) {
                    workingOnPart.put(part, part.getTimeToComplete());
                }
            }
            Set<Parts> partsDoneOnThisSecondDone = new HashSet<>();
            workingOnPart.entrySet().forEach(entry -> {
                int timeToComplete = entry.getValue();
                if (timeToComplete > 1) {
                    entry.setValue(--timeToComplete);
                } else {
                    partsDone.add(entry.getKey());
                    partsDoneOnThisSecondDone.add(entry.getKey());
                }
            });
            partsDoneOnThisSecondDone.forEach(workingOnPart::remove);
            ++seconds;
        }
        return seconds;
    }

    private class Parts implements Comparable<Parts> {
        private String partName;
        private int timeToComplete;
        private Set<Parts> dependantOn = new HashSet<>();

        public Parts(String partName) {
            this.partName = partName;
            this.timeToComplete = partName.charAt(0) - 4;
        }

        public Set<Parts> getDependents() {
            return dependantOn;
        }

        public String getPartName() {
            return partName;
        }

        public void addDependency(Parts dependency) {
            dependantOn.add(dependency);
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Parts parts = (Parts) o;
            return partName.equals(parts.partName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(partName);
        }

        @Override
        public int compareTo(Parts o) {
            return this.getPartName().compareTo(o.getPartName());
        }

        public Integer getTimeToComplete() {
            return timeToComplete;
        }
    }

}
