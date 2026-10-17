package Assignment_09_Strategy;

import Assignment_09_Strategy.Strategy.SortingStrategy;

import java.util.Random;

public class PerformanceTester {
    private static final int SMALL_SIZE = 30;
    private static final int LARGE_SIZE = 100_000;

    public void runTests(SortingStrategy[] strategies) {
        Random random = new Random();

        int[] smallArray = generateRandomArray(SMALL_SIZE, random);
        int[] largeArray = generateRandomArray(LARGE_SIZE, random);

        runForDataSet("Small dataset", smallArray, strategies);
        runForDataSet("Large dataset", largeArray, strategies);
    }

    private int[] generateRandomArray(int size, Random random) {
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(200_001) - 100_000;
        }

        return array;
    }

    private void runForDataSet(
            String name,
            int[] originalArray,
            SortingStrategy[] strategies) {

        System.out.println("\n" + name
                + " (" + originalArray.length + " elements)");
        System.out.println("----------------------------------------");

        for (SortingStrategy strategy : strategies) {
            int[] arrayToSort = originalArray.clone();

            long startTime = System.nanoTime();
            strategy.sort(arrayToSort);
            long endTime = System.nanoTime();

            if (!isSorted(arrayToSort)) {
                throw new IllegalStateException(
                        strategy.getClass().getSimpleName()
                                + " did not sort the array correctly.");
            }

            double elapsedMs = (endTime - startTime) / 1_000_000.0;

            System.out.printf("%-15s %10.3f ms%n",
                    strategy.getClass().getSimpleName(),
                    elapsedMs);
        }
    }

    private boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }

        return true;
    }
}
