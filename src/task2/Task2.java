package task2;

import java.io.*;
import java.util.Objects;

public class Task2 {
    static void circlePoints(String pathToStartRadius, String pathToCoordinates) throws Exception {
        InputStream startRadiusStream = Task2.class.getClassLoader().getResourceAsStream(pathToStartRadius);
        InputStream coordinatesStream = Task2.class.getClassLoader().getResourceAsStream(pathToCoordinates);

        BufferedReader startRadiusReader =
                new BufferedReader(new InputStreamReader(Objects.requireNonNull(startRadiusStream)));
        BufferedReader coordinatesReader =
                new BufferedReader(new InputStreamReader(Objects.requireNonNull(coordinatesStream)));

        String[] tempLine = startRadiusReader.readLine().split(" ");
        int x = Integer.parseInt(tempLine[0]);
        int y = Integer.parseInt(tempLine[1]);
        int radius = Integer.parseInt(startRadiusReader.readLine());

        String tempNextLine;
        while ((tempNextLine = coordinatesReader.readLine()) != null) {
            int xPoint = Integer.parseInt(tempNextLine.split(" ")[0]);
            int yPoint = Integer.parseInt(tempNextLine.split(" ")[1]);

            double expression = Math.pow(x - xPoint, 2) + Math.pow(y - yPoint, 2);

            if (expression < Math.pow(radius, 2)) {
                System.out.println(1);
            } else if (expression == Math.pow(radius, 2)) {
                System.out.println(0);
            } else {
                System.out.println(2);
            }
        }
        startRadiusReader.close();
        coordinatesReader.close();
    }
    public static void main(String[] args) throws Exception {
        circlePoints(args[0], args[1]);
    }
}
