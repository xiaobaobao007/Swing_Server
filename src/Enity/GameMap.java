package Enity;

import java.awt.Point;
import java.util.Random;

public class GameMap {

    public static GameMap gamemap = new GameMap();

    private static int[][][] map1 = {{{66, 0}, {66, 15}, {82, 24}, {86, 33}, {42, 80}, {69, 79}, {100, 40}, {98, 25}, {76, 8}, {76, 0}}, {{0, 79}, {119, 79}, {119, 45}, {75, 45}, {70, 13}, {39, 13}, {0, 44}}, {{120, 79}, {119, 75}, {72, 41}, {66, 34}, {52, 31}, {37, 36}, {39, 46}, {56, 50}, {101, 79}}, {{37, 79}, {47, 69}, {14, 51}, {61, 37}, {56, 31}, {40, 36}, {19, 25}, {69, 20}, {70, 35}, {88, 21}, {93, 11}, {107, 11}, {111, 20}, {97, 23}, {68, 51}, {82, 51}, {66, 74}, {55, 71}, {48, 79}}, {{0, 79}, {0, 70}, {37, 49}, {20, 39}, {40, 29}, {54, 33}, {48, 26}, {64, 17}, {78, 25}, {107, 12}, {119, 18}, {93, 32}, {109, 40}, {94, 50}, {79, 45}, {86, 53}, {67, 63}, {49, 54}, {5, 79}}, {{0, 57}, {45, 26}, {98, 57}, {83, 78}, {0, 79}}, {{52, 30}, {17, 50}, {53, 70}, {88, 51}}, {{0, 0}, {0, 27}, {25, 41}, {60, 47}, {74, 37}, {104, 30}, {103, 25}, {76, 28}, {65, 1}}, {{48, 79}, {34, 53}, {47, 44}, {8, 0}, {104, 0}, {60, 36}, {75, 69}, {87, 79}}, {{0, 41}, {21, 52}, {36, 46}, {84, 19}, {112, 37}, {113, 56}, {87, 63}, {72, 68}, {41, 49}, {17, 67}, {0, 66}}};

    private static int width = 121;
    private static int height = 81;
    static int[][] location = new int[width][height];

    private GameMap() {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                location[i][j] = 0;
            }
        }

    }

    public Point getPoint(int index) {
        Point point = new Point();
        Random random = new Random();
        int testx;
        int testy;

        while (true) {

            testx = random.nextInt(70) + 25;
            testy = random.nextInt(30) + 25;
            if (map_collision(index, testx, testy) && map_collision(index, testx, testy + 1) && map_collision(index, testx - 1, testy) && map_collision(index, testx + 1, testy) && map_collision(index, testx, testy - 1)) {
                break;
            }
        }
        point.setLocation(testx, testy);
        return point;
    }

    boolean map_collision(int index, float testx, float testy) {
        int nvert = map1[index].length;
        int[][] ver = map1[index];
        int i, j;
        boolean c = false;
        for (i = 0, j = nvert - 1; i < nvert; j = i++) {
            if (((ver[i][1] > testy) != (ver[j][1] > testy)) && (testx < (ver[j][0] - ver[i][0]) * (testy - ver[i][1]) / (ver[j][1] - ver[i][1]) + ver[i][0]))
                c = !c;
        }
        return c;

    }

    public void test() {
        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1[i].length; j++) {
                map1[i][j][0] /= 10;
                map1[i][j][1] /= 10;
            }
            StringBuffer stringBuffer = new StringBuffer(i + "{");
            for (int ii = 0; ii < map1[i].length; ii++) {// {16, 2},
                stringBuffer.append("{").append(map1[i][ii][0]).append(",").append(map1[i][ii][1]).append("}");
                if (ii != map1.length - 1) {
                    stringBuffer.append(",");
                }
            }
            stringBuffer.append("}");
            System.out.println(stringBuffer.toString());
        }
    }

}
