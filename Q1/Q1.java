import java.util.HashMap;

class Q1 {

    public static int binarySearch(int[] arr, int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] < x) {
                return binarySearch(arr, mid + 1, r, x);
            } else {
                return binarySearch(arr, l, mid, x); // change mid to mid - 1
            }
        } else {
            return -1;
        }
    }

    public static int debugBinarySearch(int[] arr, int l, int r, int x, HashMap<String, Integer> preds) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x) {
                preds.put("arr[mid] == x", preds.get("arr[mid] == x") + 1);
                return mid;
            } else if (arr[mid] < x) {
                preds.put("arr[mid] < x", preds.get("arr[mid] < x") + 1);
                return debugBinarySearch(arr, mid + 1, r, x, preds);
            } else {
                preds.put("arr[mid] > x", preds.get("arr[mid] > x") + 1);
                return debugBinarySearch(arr, l, mid, x, preds); // change mid to mid - 1
            }
        } else {
            return -1;
        }
    }

    public static int factorial(int n) {
        /*
         * user can pass in negative inputs, causing stack overflows
         * large inputs, return 0
         */
        if (n == 0) { // change n == 0 to n <= 0
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static int debugFactorial(int n, HashMap<String, Integer> preds) {
        if (n == 0) { // change n == 0 to n <= 0
            preds.put("n == 0", preds.get("n == 0") + 1);
            return 1;
        } else {
            if (n < 0)
                preds.put("n < 0", preds.get("n < 0") + 1);
            else
                preds.put("n > 0", preds.get("n > 0") + 1);
            return n * factorial(n - 1);
        }
    }

    public static void bubbleSort(int[] arr, int limit) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > limit) {
                    return;
                }
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 4, 5, 8, 9 };
        System.out.println(binarySearch(arr, 0, arr.length, 3));
        // int result = factorial(5);
        // System.out.println(result);
        // bubbleSort(null, 2);
    }
}