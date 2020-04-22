package day11;

import java.util.*;

public class ChronalCharge {
    private int gridSerialNumber;
    private List<FuelCell> fuelCellList;

    public ChronalCharge(int gridSerialNumber) {
        this.gridSerialNumber = gridSerialNumber;
        this.fuelCellList = generateCellGridList();
    }

    List<FuelCell> generateCellGridList() {
        List<FuelCell> fuelCells = new ArrayList<>();
        for (int y = 1; y <= 300; ++y) {
            for (int x = 1; x <= 300; ++x) {
                fuelCells.add(new FuelCell(x, y));
            }
        }
        return fuelCells;
    }

    Map<FuelCell, Integer> getMaxSquarePowerOfAnySize() {
        Map<FuelCell, Integer> maxPowerMap = new HashMap<>();
        int maxPowerValue = 0;
        for (int squareSize = 3; squareSize <= 20; ++squareSize) {
            for (int x = 0; x <= 300 - squareSize; ++x) {
                for (int y = 0; y <= 300 - squareSize; ++y) {
                    int startCellId = x + 300 * y;
                    FuelCell startCell = fuelCellList.get(startCellId);
                    int squarePower = getSquarePowerForCell(startCellId, squareSize);
                    if (squarePower >= maxPowerValue) {
                        maxPowerValue = squarePower;
                        maxPowerMap.clear();
                        maxPowerMap.put(startCell, squareSize);
                    }
                }
            }
        }
        return maxPowerMap;
    }

    FuelCell getMaxSquarePowerFuelCellOfThree() {
        int squareSize = 3;
        Map<FuelCell, Integer> squarePowerMap = new HashMap<>();
        for (int x = 0; x <= 300 - squareSize; ++x) {
            for (int y = 0; y <= 300 - squareSize; ++y) {
                int startCellId = x + (300 * y);
                FuelCell startCell = fuelCellList.get(startCellId);
                int squarePower = getSquarePowerForCell(startCellId, squareSize);
                squarePowerMap.put(startCell, squarePower);
            }
        }
        return Collections.max(squarePowerMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    int getSquarePowerForCell(int startCellId, int squareSize) {
        int squarePower = 0;
        for (int x = 0; x < squareSize; ++x) {
            for (int y = 0; y < squareSize; ++y) {
                int squareIndex = startCellId + 300 * y + x;
                FuelCell fuelCell = fuelCellList.get(squareIndex);
                squarePower += fuelCell.getPowerLevel();
            }
        }
        return squarePower;
    }

    class FuelCell {
        private int xCoordinate;
        private int yCoordinate;
        private int powerLevel;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FuelCell fuelCell = (FuelCell) o;
            return xCoordinate == fuelCell.xCoordinate &&
                    yCoordinate == fuelCell.yCoordinate &&
                    powerLevel == fuelCell.powerLevel;
        }

        @Override
        public int hashCode() {
            return Objects.hash(xCoordinate, yCoordinate, powerLevel);
        }

        public FuelCell(int xCoordinate, int yCoordinate) {
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
            int rackId = xCoordinate + 10;
            int helperPower = rackId * yCoordinate;
            helperPower += gridSerialNumber;
            helperPower *= rackId;
            if (helperPower == 0) {
                this.powerLevel = -5;
            } else {
                helperPower = (helperPower / 100) % 10;
                this.powerLevel = helperPower - 5;
            }
        }

        public int getPowerLevel() {
            return powerLevel;
        }

        public int getxCoordinate() {
            return xCoordinate;
        }

        public int getyCoordinate() {
            return yCoordinate;
        }
    }
}
