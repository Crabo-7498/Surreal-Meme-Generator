package Util;

/**
 * Tuple class
 * @param <X> Item X
 * @param <Y> Item Y
 */
public class Tuple<X, Y> {
    public final X x;
    public final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
}
