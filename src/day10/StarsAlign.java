package day10;

import java.util.*;

public class StarsAlign {
    List<Point> points = new ArrayList<>();
    private int minX = -80;
    private int minY = -10;
    private int maxX = 80;
    private int maxY = 10;

    class Point implements Comparable<Point>{

        private int positionX;
        private int positionY;
        private int velocityX;
        private int velocityY;

        public Point(int positionX, int positionY) {
            this.positionX = positionX;
            this.positionY = positionY;
        }

        public Point(int positionX, int positionY, int velocityX, int velocityY) {
            this.positionX = positionX;
            this.positionY = positionY;
            this.velocityX = velocityX;
            this.velocityY = velocityY;
        }

        void nextSecond(){
            this.positionX += this.velocityX;
            this.positionY += this.velocityY;
        }
        public int getPositionX() {
            return positionX;
        }

        public int getPositionY() {
            return positionY;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return positionX == point.positionX &&
                    positionY == point.positionY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(positionX, positionY);
        }

        @Override
        public int compareTo(Point o) {
            if(this.positionX == o.positionX){
                return this.positionY - o.positionY;
            } else {
                return this.positionX - o.positionX;
            }
        }
    }

    void parsePoint(String line) {
        String[] lineSplit = line.split(">");
        String[] positions = lineSplit[0].replace("position=<","").trim().split(",");
        String[] velocities = lineSplit[1].replace("velocity=<","").trim().split(",");
        int positionX = Integer.parseInt(positions[0].trim());
        int positionY = Integer.parseInt(positions[1].trim());
        int velocityX = Integer.parseInt(velocities[0].trim());
        int velocityY = Integer.parseInt(velocities[1].trim());

        Point point = new Point(positionX, positionY, velocityX, velocityY);
        points.add(point);
    }

    void printStars() {
        getBoundries();
        if(Math.abs(maxY - minY) < 15) {
            for (int y = minY; y <= maxY; ++y) {
                for (int x = minX; x <= maxX; ++x) {
                    if (points.contains(new Point(x, y))) {
                        System.out.print("#");
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    void nextSecond(){
        points.forEach(Point::nextSecond);
    }

    private void getBoundries() {
        IntSummaryStatistics xStatistics = points.stream().mapToInt(Point::getPositionX).summaryStatistics();
        IntSummaryStatistics yStatistics = points.stream().mapToInt(Point::getPositionY).summaryStatistics();
        minX = xStatistics.getMin();
        minY = yStatistics.getMin();
        maxX = xStatistics.getMax();
        maxY = yStatistics.getMax();
    }
}
