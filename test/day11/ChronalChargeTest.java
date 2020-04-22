package day11;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ChronalChargeTest {

    @Test
    public void testPowerLevel1(){
        int serialNumber = 8;
        ChronalCharge chronalCharge = new ChronalCharge(serialNumber);
        ChronalCharge.FuelCell fuelCell = chronalCharge.new FuelCell(3,5);
        assertEquals(4, fuelCell.getPowerLevel());
    }

    @Test
    public void testPowerLevel2(){
        int serialNumber = 57;
        ChronalCharge chronalCharge = new ChronalCharge(serialNumber);
        ChronalCharge.FuelCell fuelCell = chronalCharge.new FuelCell(122,79);
        assertEquals(-5, fuelCell.getPowerLevel());
    }

    @Test
    public void testPowerLevel3(){
        int serialNumber = 39;
        ChronalCharge chronalCharge = new ChronalCharge(serialNumber);
        ChronalCharge.FuelCell fuelCell = chronalCharge.new FuelCell(217,196);
        assertEquals(0, fuelCell.getPowerLevel());
    }

    @Test
    public void testPowerLevel4(){
        int serialNumber = 71;
        ChronalCharge chronalCharge = new ChronalCharge(serialNumber);
        ChronalCharge.FuelCell fuelCell = chronalCharge.new FuelCell(101,153);
        assertEquals(4, fuelCell.getPowerLevel());
    }

    @Test
    public void testMaxSquarePowerPart2(){
        int serialNumber = 18;
        ChronalCharge chronalCharge = new ChronalCharge(serialNumber);
        Map<ChronalCharge.FuelCell, Integer> fuelCellIntegerMap = chronalCharge.getMaxSquarePowerOfAnySize();
        assertEquals(113, chronalCharge.getSquarePowerForCell(89+ (300*268), 16));
        assertEquals(90, fuelCellIntegerMap.keySet().stream().findFirst().get().getxCoordinate());
        assertEquals(269, fuelCellIntegerMap.keySet().stream().findFirst().get().getyCoordinate());
        assertEquals(16, fuelCellIntegerMap.values().stream().findFirst().get());
    }

    @Test
    public void testMaxSquarePowerPart2Example2(){
        int serialNumber = 42;
        ChronalCharge chronalCharge = new ChronalCharge(serialNumber);
        Map<ChronalCharge.FuelCell, Integer> fuelCellIntegerMap = chronalCharge.getMaxSquarePowerOfAnySize();
        assertEquals(119, chronalCharge.getSquarePowerForCell(231+ (300*250), 12));
        assertEquals(232, fuelCellIntegerMap.keySet().stream().findFirst().get().getxCoordinate());
        assertEquals(251, fuelCellIntegerMap.keySet().stream().findFirst().get().getyCoordinate());
        assertEquals(12, fuelCellIntegerMap.values().stream().findFirst().get());
    }

    @Test
    public void testPuzzle(){
        int serialNumber = 3031;
        ChronalCharge chronalCharge = new ChronalCharge(serialNumber);
        ChronalCharge.FuelCell fuelCell = chronalCharge.getMaxSquarePowerFuelCellOfThree();
        assertEquals(21, fuelCell.getxCoordinate());
        assertEquals(76, fuelCell.getyCoordinate());
    }

    @Test
    public void testPuzzlePart2(){
        int serialNumber = 3031;
        ChronalCharge chronalCharge = new ChronalCharge(serialNumber);
        Map<ChronalCharge.FuelCell, Integer> fuelCellIntegerMap = chronalCharge.getMaxSquarePowerOfAnySize();
        assertEquals(234, fuelCellIntegerMap.keySet().stream().findFirst().get().getxCoordinate());
        assertEquals(108, fuelCellIntegerMap.keySet().stream().findFirst().get().getyCoordinate());
        assertEquals(16, fuelCellIntegerMap.values().stream().findFirst().get());
    }
}