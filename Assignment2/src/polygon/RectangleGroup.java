package polygon;

import java.util.Arrays;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

final class RectangleGroup<S> {
    private final Set<Rectangle<S>> rectangleSet;
    private final PlaneMap<S> planeMap;
    private final NavigableMap<IndexPair, Long> matrixGrid;
    private final boolean isOverlapping;
    /**
     * getter for the rectangleSet field
     * @return Set<Rectangle>
     */
    public Set<Rectangle<S>> getRectangleSet() {
        return rectangleSet;
    }

    /**
     * getter for the planeMap field
     * @return PlaneMap
     */
    public PlaneMap<S> getPlaneMap() {
        return planeMap;
    }
    //private RectangleGroup constructor that does not throw an exception
    private RectangleGroup(Set<Rectangle<S>> rectangleSet, PlaneMap<S> planeMap, NavigableMap<IndexPair, Long> matrixGrid, boolean isOverlapping) {
        this.rectangleSet = rectangleSet;
        this.planeMap = planeMap;
        this.matrixGrid = matrixGrid;
        this.isOverlapping = isOverlapping;
    }
    /**
     * Method to create a RectangleGroup from an input set of Rectangles
     * @param rectangles set of rectangles to create a RectangleGroup
     * @return RectangleGroup
     */
    static <S extends Comparable<S>> RectangleGroup<S> from(Set<Rectangle<S>> rectangles) {
        //verify rectangles in set are non-null
        verifyNonNull(rectangles);

        //make a PlaneMap based on the set of rectangles
        PlaneMap<S> planeMap = PlaneMap.from(rectangles);

        //make a Grid based on the PlaneMap indexes of the horizontal and vertical AxisMaps
        Grid grid = Grid.from(Rectangle.of(0, planeMap.xSize()-1, 0, planeMap.ySize()-1));

        //make the NavigableMap with <IndexPair, 0L>, where IndexPairs are from iterating through the Grid
        NavigableMap<IndexPair, Long> matrixGrid = RectangleGroup.initializeMatrixGrid(grid);

        for(Rectangle<S> rectangle: rectangles) {
            //make Grid based off rectangle
            int left = planeMap.indexOf(rectangle.left(),true);
            int right = planeMap.indexOf(rectangle.right(), true);
            int bottom = planeMap.indexOf(rectangle.bottom(),false);
            int top = planeMap.indexOf(rectangle.top(), false);
            Grid rectangleGrid = Grid.from(Rectangle.of(left, right, bottom ,top));
            RectangleGroup.incrementValues(rectangleGrid, matrixGrid);
        }
        boolean isOverlapping = RectangleGroup.checkForOverlap(matrixGrid);

        return new RectangleGroup<>(rectangles, planeMap, matrixGrid, isOverlapping);
    }

    /**
     * Getter method for isOverlapping field
     * @return boolean indicating if rectangles overlap in this Rectangle Group
     */
    public boolean isOverlapping() {
        return this.isOverlapping;
    }
    //helper method to initialize the NavigableMap with the IndexPairs of the input Grid's iterator
    private static NavigableMap<IndexPair, Long> initializeMatrixGrid(Grid grid) {
        NavigableMap<IndexPair, Long> matrixGrid = new TreeMap<>();
        for(IndexPair next: grid) {
            matrixGrid.put(next, 0L);
        }
        return matrixGrid;
    }
    //helper method to check if rectangles are overlapping in this RectangleGroup
    private static boolean checkForOverlap(NavigableMap<IndexPair, Long> matrixGrid ) {
        for(IndexPair indexPair: matrixGrid.keySet()) {
            if(matrixGrid.get(indexPair) > 1) {
                return true;
            }
        }
        return false;
    }
    //helper method to increment IndexPair keys in matrixGrid
    private static void incrementValues(Grid rectangleGrid, NavigableMap<IndexPair, Long> matrixGrid) {
        for(IndexPair next: rectangleGrid) {
            matrixGrid.replace(next, matrixGrid.get(next)+1);
        }
    }
    /**
     * Getter method for matrixGrid field
     * @return NavigableMap<IndexPair, Long> that represents the matrix grid of this RectangleGroup
     */
    public NavigableMap<IndexPair, Long> getMatrixGrid() {
        return this.matrixGrid;
    }
    //helper method to verify Rectangle.from inputs are non-null
    private static <S> void verifyNonNull(Set<Rectangle<S>> set) {
        for(Rectangle<S> rectangle: set) {
            if(rectangle == null) {
                throw new IllegalArgumentException(new RectangleException(RectangleException.Error.NULL_POINTERS));
            }
        }
    }
    public String matrixGridToString() {
        long[][] matrix = new long[getPlaneMap().ySize()-1][getPlaneMap().xSize()-1];
        for(IndexPair next: getMatrixGrid().keySet()) {
            matrix[next.yIndex()][next.xIndex()] = getMatrixGrid().get(next);
        }
        StringBuilder builder = new StringBuilder();
        for (long[] row : matrix) {
            builder.append(Arrays.toString(row));
            builder.append("\n");
        }
        return builder.toString();
    }
}
