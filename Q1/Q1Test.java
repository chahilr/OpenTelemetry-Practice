import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.rules.Timeout;

public class Q1Test {

    @Test
    public void testBinarySearch() {
        String[] preds = { "arr[mid] == x", "arr[mid] < x", "arr[mid] > x" };
        HashMap<String, Integer> predsObservedCount = new HashMap<>();
        HashMap<String, Integer> predsSuccessCount = new HashMap<>();
        HashMap<String, Integer> predsFailureCount = new HashMap<>();
        HashMap<String, Integer> predsObservedSuccessCount = new HashMap<>();
        HashMap<String, Integer> predsObservedFailureCount = new HashMap<>();

        for (String pred : preds) {
            predsFailureCount.put(pred, 0);
            predsSuccessCount.put(pred, 0);
            predsObservedFailureCount.put(pred, 0);
            predsObservedSuccessCount.put(pred, 0);
        }

        int[] arr1 = { 1, 2, 3, 4, 5, 6, 7 };
        int[] arr2 = { 2, 4, 6, 8, 10 };
        int[] arr3 = { 3, 5, 7, 9 };
        int[][] inputArrays = { arr1, arr2, arr3 };
        int[] searchValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        for (int i = 0; i < inputArrays.length; i++) {
            for (int j = 0; j < searchValues.length; j++) {
                for (String pred : preds) {
                    predsObservedCount.put(pred, 0);
                }
                try {
                    Q1.debugBinarySearch(inputArrays[i], 0, inputArrays[i].length - 1, searchValues[j],
                            predsObservedCount);
                    for (String pred : preds) {
                        if (predsObservedCount.get(pred) > 0) {
                            increment(predsSuccessCount, pred);
                        }
                        increment(predsObservedSuccessCount, pred);
                    }
                    printRun("SUCCESS", i * searchValues.length + j, inputArrays[i], searchValues[j],
                            predsObservedCount);

                } catch (StackOverflowError e) {
                    for (String pred : preds) {
                        if (predsObservedCount.get(pred) > 0) {
                            increment(predsFailureCount, pred);
                        }
                        increment(predsObservedFailureCount, pred);
                    }
                    printRun("FAILURE", i * searchValues.length + j, inputArrays[i], searchValues[j],
                            predsObservedCount);
                }
            }
        }
        for (String pred : preds) {
            int successes = predsSuccessCount.get(pred);
            int failures = predsFailureCount.get(pred);
            double failure = (double) failures / (failures + successes);
            int failuresObserved = predsObservedFailureCount.get(pred);
            int successesObserved = predsObservedSuccessCount.get(pred);
            double context = (double) failuresObserved / (failuresObserved + successesObserved);
            // System.out.printf("\nPredicate: %s\nSuccessful Runs: %d\nFailure Runs: %d\n",
            // pred,
            // successes,
            // failures);
            System.out.printf(
                    """
                            \nPredicate: %s
                            Successful Runs: %d
                            Failure Runs: %d
                            Failure: %.2f
                            Context: %.2f
                            Increase: %.2f
                            """,
                    pred, successes, failures, failure, context, failure - context);
        }
    }

    private void increment(HashMap<String, Integer> predsMap, String pred) {
        predsMap.put(pred, predsMap.get(pred) + 1);
    }

    private void printRun(String outcome, int run, int[] arr, int searchKey, HashMap<String, Integer> preds) {
        System.out.printf("\nRun %d: %s\nArray: %s\nKey: %d\nPredicates: %s\n", run, outcome, Arrays.toString(arr),
                searchKey, preds);

    }

    private int search(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x)
                return i;
        }
        return -1;
    }

    @Test
    public void testBubbleSort() {
        int[] arr = { 5, 2, 3, 1, 4 };
        Q1.bubbleSort(arr, 5);
        for (int elem : arr) {
            System.out.print(elem + ", ");
        }
    }

    @Test
    public void testFactorial() {
        String[] preds = { "n == 0", "n < 0", "n > 0" };
        HashMap<String, Integer> predsObservedCount = new HashMap<>();
        HashMap<String, Integer> predsSuccessCount = new HashMap<>();
        HashMap<String, Integer> predsFailureCount = new HashMap<>();
        HashMap<String, Integer> predsObservedSuccessCount = new HashMap<>();
        HashMap<String, Integer> predsObservedFailureCount = new HashMap<>();

        for (String pred : preds) {
            predsFailureCount.put(pred, 0);
            predsSuccessCount.put(pred, 0);
            predsObservedFailureCount.put(pred, 0);
            predsObservedSuccessCount.put(pred, 0);
        }

        for (int n = -10; n <= 10; n++) {
            for (String pred : preds) {
                predsObservedCount.put(pred, 0);
            }
            try {
                Q1.debugFactorial(n, predsObservedCount);
                for (String pred : preds) {
                    if (predsObservedCount.get(pred) > 0) {
                        increment(predsSuccessCount, pred);
                    }
                    increment(predsObservedSuccessCount, pred);
                }
                System.out.printf("\nRun %d: SUCCESS\nn: %d\nPredicates: %s\n", n + 10, n, Arrays.toString(preds));
            } catch (StackOverflowError e) {
                for (String pred : preds) {
                    if (predsObservedCount.get(pred) > 0) {
                        increment(predsFailureCount, pred);
                    }
                    increment(predsObservedFailureCount, pred);
                }
                System.out.printf("\nRun %d: FAILURE\nn: %d\nPredicates: %s\n", n + 10, n, Arrays.toString(preds));
            }
        }

        for (String pred : preds) {
            int successes = predsSuccessCount.get(pred);
            int failures = predsFailureCount.get(pred);
            double failure = (double) failures / (failures + successes);
            int failuresObserved = predsObservedFailureCount.get(pred);
            int successesObserved = predsObservedSuccessCount.get(pred);
            double context = (double) failuresObserved / (failuresObserved + successesObserved);

            System.out.printf(
                    """
                            \nPredicate: %s
                            Successful Runs: %d
                            Failure Runs: %d
                            Failure: %.2f
                            Context: %.2f
                            Increase: %.2f
                            """,
                    pred, successes, failures, failure, context, failure - context);
        }
    }
}
