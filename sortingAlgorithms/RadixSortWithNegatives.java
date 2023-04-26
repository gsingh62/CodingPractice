class RadixSortWithNegatives {
    public int[] sortArray(int[] arr) {
        radixSort(arr);
        return arr;
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
        ArrayList<Integer> negatives = new ArrayList();
        ArrayList<Integer> positives = new ArrayList();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                negatives.add(arr[i] * -1);
            } else {
                positives.add(arr[i]);
            }
        }
        int[] posArray = positives.stream().mapToInt(k -> k).toArray();
        int posMax = getMax(posArray);
        for (int exp = 1; posMax/exp > 0; exp *= 10)
            countSort(posArray, exp);
        int negArrayLength = 0;
        if (!negatives.isEmpty()) {
            int[] negArray = negatives.stream().mapToInt(k -> k).toArray();
            negArrayLength = negArray.length;
            int negMax = getMax(negArray);
            for (int exp = 1; negMax/exp > 0; exp *= 10)
                countSort(negArray, exp);
            for (int i = 0; i < negArrayLength; i++) {
                arr[i] = negArray[negArrayLength - 1 - i] * -1;
            }
        }

        for (int i = 0; i < posArray.length; i++) {
            arr[i + negArrayLength] = posArray[i];
        }
    }
}
