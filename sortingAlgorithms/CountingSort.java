import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        int[] arr = { 170, 45, 75, 90, 802, 24, 2, 66 };
        countSort(arr);

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

    private static int getMin(int[] arr) {
        int min = arr[0];
        for(int item: arr) {
            if (item < min) {
                min = item;
            }
        }
        return min;
    }

    private static void countSort(int[] arr) {
        int n = arr.length;
        int i;
        int min = getMin(arr);
        int max = getMax(arr);
        int[] count = new int[max - min + 1];
        int[] output = new int[n];
        Arrays.fill(count, 0);

        for (i = 0; i < n; i++) {
            count[arr[i] - min]++;
        }

        for (i = 1; i < max - min + 1; i++) {
            count[i] += count[i-1];
        }

        for (i = n -1; i >= 0; i--) {
            output[count[arr[i] - min] -1] = arr[i];
            --count[arr[i] - min];
        }

        for (i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
}
