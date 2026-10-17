package Assignment_09_Strategy;

import Assignment_09_Strategy.Strategy.BubbleSort;
import Assignment_09_Strategy.Strategy.InsertionSort;
import Assignment_09_Strategy.Strategy.MergeSort;
import Assignment_09_Strategy.Strategy.SortingStrategy;

public class Main {
    public static void main(String[] args) {
        SortingStrategy[] strategies = {
                new BubbleSort(),
                new InsertionSort(),
                new MergeSort()
        };

        PerformanceTester tester = new PerformanceTester();
        tester.runTests(strategies);
    }
}
