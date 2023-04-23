import java.util.Arrays;

/**
* Best time complexity: All elements have the same number of digits.
* Average case time Complexity: O(d * (n + b)) - b is base (e.g 10 for decimal digits, 2 for binary),  n is number of elements to be sorted, d is 
* the number of digits in the maximum number.
* Worst case time complexity: O (logb (mx)(n + b)) - only one element which has significantly large number of digits.
* Space Complexity: O (n + b)
*/
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = { 170, 45, 75, 90, 802, 24, 2, 66 };
        radixSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for(int item: arr) {
            if (item > max) {
                max = item;
            }
        }
        return max;
    }

    private static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int i;
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < n; i++) {
            int currDigit = (arr[i] /exp) % 10;
            count[currDigit]++;
        }

        for (i = 1; i < 10; i++) {
            count[i] += count[i-1];
        }

        for (i = n - 1; i >= 0; i--) {
            int currDigit = (arr[i] /exp) % 10;
            output[count[currDigit] - 1] = arr[i];
            count[currDigit]--;
        }

        for (i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    private static void radixSort(int[] arr) {
        int max = getMax(arr);

        for (int exp = 1; max/exp > 0; exp *= 10)
            countSort(arr, exp);
    }
}
