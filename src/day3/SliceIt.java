package day3;

import java.util.*;

public class SliceIt {
    private class SliceCoordinates {
        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        private int x;
        private int y;

        public SliceCoordinates(int x, int y) {
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException("Negative coordinates does not make sense");
            }
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SliceCoordinates that = (SliceCoordinates) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return x*y;
        }
    }

    private Set<SliceCoordinates> fabric = new HashSet<>();
    private Set<SliceCoordinates> overlapping = new HashSet<>();
    private Map<SliceCoordinates, List<Integer>> fabricClaims = new HashMap<>();
    private Set<Integer> notOverlappingIds = new HashSet<>();

    public void parseClaim(String claim) {
        String[] claimArray = claim.split(" ");
        if (claimArray.length != 4) {
            throw new IllegalArgumentException();
        }
        int id = Integer.parseInt(claimArray[0].substring(1));
        String[] padding = claimArray[2].replace(":", "").split(",");
        int paddingLeft = Integer.parseInt(padding[0]);
        int paddingTop = Integer.parseInt(padding[1]);
        String[] widthHeight = claimArray[3].split("x");
        int width = Integer.parseInt(widthHeight[0]);
        int height = Integer.parseInt(widthHeight[1]);
        createSliceClaim(id, paddingLeft, paddingTop, width, height);
    }

    private void createSliceClaim(int id, int paddingLeft, int paddingTop, int width, int height) {
        notOverlappingIds.add(id);
        for (int x = paddingLeft; x < paddingLeft + width; x++) {
            for (int y = paddingTop; y < paddingTop + height; y++) {
                SliceCoordinates sliceCoordinates = new SliceCoordinates(x, y);
                if (!fabric.add(sliceCoordinates)){
                    overlapping.add(sliceCoordinates);
                    notOverlappingIds.remove(id);
                    fabricClaims.get(sliceCoordinates).add(id);
                } else{
                    fabricClaims.put(sliceCoordinates, new ArrayList<>(Arrays.asList(id)));
                }
            }
        }
    }

    public long getOverlappingCount() {
//        System.out.println(fabric.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(p->p.getValue() > 1).count());
//        return fabric.stream().filter(i -> Collections.frequency( fabric, i) > 1).distinct().count();
        return overlapping.stream().distinct().count();
    }

    public long findIntactID(){
//        List<Integer> ids = fabric.stream().mapToInt(SliceCoordinates::getId).boxed().distinct().collect(Collectors.toList());
        for(Integer id: notOverlappingIds){
            if(fabricClaims.values().stream().filter(list -> list.contains(id)).allMatch(list -> list.size() ==1)){
                return id;
            }
        }
        return 0;
    }
}
