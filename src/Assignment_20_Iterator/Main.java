package Assignment_20_Iterator;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        FibonacciSequence sequence = new FibonacciSequence();

        Iterator<Long> iterator = sequence.iterator();

        for (long i = 0; i < 100; i++) {

            if (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

        }

    }

}