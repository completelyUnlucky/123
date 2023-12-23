package task4;

import java.io.*;
import java.util.*;
import java.util.List;

public class Task4 {
    static int minMovesTo(InputStream fileWithNumbers) {
        // Работа с файлом
        BufferedReader fileWithNumbersReader = new BufferedReader(new InputStreamReader(fileWithNumbers));
        Scanner scanner = new Scanner(fileWithNumbersReader);
        List<Integer> numsList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            numsList.add(Integer.parseInt(scanner.nextLine()));
        }

        // Работа с данными списка
        int moves = 0;
        Collections.sort(numsList);
        int median = numsList.get(numsList.size() / 2);
        for (int num : numsList) {
            moves += Math.abs(num - median);
        }
        return moves;
    }

    public static void main(String[] args) {
        InputStream startRadiusStream = Task4.class.getClassLoader().getResourceAsStream(args[0]);
        System.out.println(minMovesTo(startRadiusStream));
    }
}
