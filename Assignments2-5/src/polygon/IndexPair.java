package polygon;

public class /*record*/ IndexPair /*(Integer xIndex, Integer yIndex)*/ implements Comparable<IndexPair> {
    private final Integer xIndex;
    private final Integer yIndex;
    public IndexPair(Integer xIndex, Integer yIndex) {
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }
    public Integer xIndex() {
        return this.xIndex;
    }
    public Integer yIndex() {
        return this.yIndex;
    }

    /**
     * Compares two IndexPair objects based on values of x coordinates and y coordinates
     * Given p1(x1, y1) and p2(x2, y2), if x1 > x2 then p1 > p2; if x1 < x2 then p1 < p2
     * If x1 = x2, then use y coordinates to compare using same logic
     * @param o the object to be compared.
     * @return positive int if this > argument, negative int if this < argument, 0 if equal
     */
    @Override
    public int compareTo(IndexPair o) {
        int diff = this.xIndex - o.xIndex();
        if(diff != 0) {
            return diff;
        }
        else {
            return this.yIndex() - o.yIndex();
        }
    }

    /**
     * Returns the Index Pair after it is incremented in direction given by the input Direction
     * @param direction direction this IndexPair is incremented in
     * @return incremented Index Pair
     */
    public IndexPair increment(Direction direction) {
        IndexPair.verifyNonNull(direction);

        if(direction.isHorizontal()) {
            return new IndexPair(this.xIndex()+this.moveByOne(direction), this.yIndex());
        }
        else {
            return new IndexPair(this.xIndex(), this.yIndex()+moveByOne(direction));
        }
    }
    //helper method for incrementing or decrementing an index pair x or y coordinate
    private int moveByOne(Direction direction) {
        return direction.isIncrement() ? 1 : -1;
    }
    //helper method for verifying for null arguments
    private static void verifyNonNull(Object... arr) {
        for(Object element: arr) {
            if(element == null) {
                throw new IllegalArgumentException("Arguments must be non-null");
            }
        }
    }

    /**
     * toString() method for IndexPair
     * @return String representation of IndexPair as a coordinate - (x, y)
     */
    @Override
    public String toString() {
        return "("+ this.xIndex() +"," + this.yIndex() + ")";
    }

    /**
     * equals() method for IndexPair comparing x and y coordinate values
     * @param o Object to compare to this IndexPair
     * @return boolean indicating with IndexPair fields are logically equivalent
     */
    @Override
    public boolean equals(Object o) {
        if(o instanceof IndexPair) {
            return this.compareTo((IndexPair) o) == 0;
        }
        else {
            return false;
        }
    }
}
       
        




