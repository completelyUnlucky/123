package task1;

import java.util.Arrays;

public class Task1 {
    static String circularMass(int n, int m) {
        StringBuilder path = new StringBuilder();
        int[] arr = new int[n];

        Arrays.setAll(arr, i -> ++i);

        int step = 0;
        do {
            path.append(arr[step]);
            step = (step + m - 1) % n;
        } while (step != 0);

        return path.toString();
    }

    public static void main(String[] args) {
        System.out.println(circularMass(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }
}