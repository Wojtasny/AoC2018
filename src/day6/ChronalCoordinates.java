package day6;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChronalCoordinates {
    private TreeSet<PointInSpace> givenPoints;
    private Set<PointInSpace> borderPoints;
    private int maxX;
    private int maxY;

    public ChronalCoordinates(Set<String> givenPoints) {
        this.givenPoints = givenPoints.stream()
                .map(s -> s.replace(",", "")
                        .split(" ")).map(strings -> new PointInSpace(Integer.parseInt(strings[0]), Integer.parseInt(strings[1])))
                .collect(Collectors.toCollection(TreeSet::new));
        this.borderPoints = getBorderPoints();
    }

    Set<PointInSpace> getBorderPoints() {
        this.maxY = givenPoints.stream().mapToInt(sx -> sx.y).max().getAsInt();
        this.maxX = givenPoints.stream().mapToInt(sx -> sx.x).max().getAsInt();

        List<PointInSpace> borderLine = new ArrayList<>();
        for (int x = -10 * maxX; x < 10 * maxX; x++) {
            PointInSpace southBorder = new PointInSpace(x, -maxY * 10);
            PointInSpace closestPointSouth = getClosestPoint(southBorder);
            if (closestPointSouth != null) {
                borderLine.add(closestPointSouth);
            }

            PointInSpace northBorder = new PointInSpace(x, maxY * 10);
            PointInSpace closestPointNorth = getClosestPoint(northBorder);
            if (closestPointNorth != null) {
                borderLine.add(closestPointNorth);
            }
        }

        for (int y = -10 * maxY; y < 10 * maxY; y++) {
            PointInSpace westBorder = new PointInSpace(-10 * maxX, y);
            PointInSpace closestPointWest = getClosestPoint(westBorder);
            if (closestPointWest != null) {
                borderLine.add(closestPointWest);
            }

            PointInSpace eastBorder = new PointInSpace(10 * maxX, y);
            PointInSpace closestPointEast = getClosestPoint(eastBorder);
            if (closestPointEast != null) {
                borderLine.add(closestPointEast);
            }
        }

        return new HashSet<>(borderLine);
    }

    long getBiggestFiniteArea() {
        if (givenPoints.isEmpty()) throw new RuntimeException();

        List<PointInSpace> wholeSpace = new ArrayList<>();
        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                PointInSpace point = new PointInSpace(x, y);
                PointInSpace closestPoint = getClosestPoint(point);
                if (closestPoint != null) {
                    wholeSpace.add(closestPoint);
                }
            }
        }
        wholeSpace.removeAll(borderPoints);
        Map<PointInSpace, Long> distribution = wholeSpace.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return Collections.max(distribution.entrySet(), Map.Entry.comparingByValue()).getValue();
    }

    private PointInSpace getClosestPoint(PointInSpace pointInSpace) {
        if (givenPoints.contains(pointInSpace)) return pointInSpace;
        Map<PointInSpace, Integer> distancesMap = getDistanceMap(pointInSpace);
        Map.Entry<PointInSpace, Integer> closestPointWithDistance = Collections.min(distancesMap.entrySet(),
                Map.Entry.comparingByValue());
        if (Collections.frequency(distancesMap.values(), closestPointWithDistance.getValue()) != 1) {
            return null;
        } else {
            return closestPointWithDistance.getKey();
        }
    }

    private Map<PointInSpace, Integer> getDistanceMap(PointInSpace pointInSpace) {
        Map<PointInSpace, Integer> distancesMap = new HashMap<>();
        givenPoints.forEach(givenPoint -> distancesMap.put(givenPoint, givenPoint.distanceFrom(pointInSpace)));
        return distancesMap;
    }

    long getRegionSize(int totalDistance) {
        Set<PointInSpace> regionOfInterest = new HashSet<>();
        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                PointInSpace point = new PointInSpace(x, y);
                Map<PointInSpace, Integer> distanceMap = getDistanceMap(point);
                if (distanceMap.values().stream().mapToInt(i -> i).sum() < totalDistance) {
                    regionOfInterest.add(point);
                }
            }
        }
        return regionOfInterest.size();
    }

    static class PointInSpace implements Comparable<PointInSpace> {
        private int x;
        private int y;

        public PointInSpace(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int distanceFrom(PointInSpace anotherPoint) {
            return Math.abs(this.x - anotherPoint.x) + Math.abs(this.y - anotherPoint.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PointInSpace that = (PointInSpace) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public int compareTo(PointInSpace o) {
            if ((this.x - o.x) != 0) {
                return this.x - o.x;
            } else {
                if ((this.y - o.y) != 0) {
                    return this.y - o.y;
                } else {
                    return 0;
                }
            }
        }
    }
}
