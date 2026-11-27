package Assignment_20_Iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FibonacciIterator implements Iterator<Long> {

    private long previous = 1;
    private long current = 1;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Long next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        long result = previous;

        long nextNumber = previous + current;

        previous = current;
        current = nextNumber;

        return result;
    }

}