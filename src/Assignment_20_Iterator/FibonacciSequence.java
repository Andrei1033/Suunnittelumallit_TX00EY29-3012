package Assignment_20_Iterator;

import java.util.Iterator;

public class FibonacciSequence implements Sequence {

    @Override
    public Iterator<Long> iterator() {
        return new FibonacciIterator();
    }

}