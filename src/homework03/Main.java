package homework03;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static Map createMap (int width, int height) {
        Map <Coordinate, Boolean> gridMap = new LinkedHashMap <>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Coordinate coordinate = new Coordinate(x, y);
                gridMap.put(coordinate, false);
            }
        }
        return gridMap;
    }

    public static void main (String args []) throws IOException {

        Console cons = System.console();

    
        String fileName = cons.readLine (">>> ");

         //loading the gol file
        Path gridPath = Paths.get("./game/" + fileName);
        File gridFile = gridPath.toFile();

        if (!gridFile.exists()) {
            System.err.println("File cannot be found");
            System.exit(1);
        }


        FileReader fr = new FileReader(gridFile);
        BufferedReader br = new BufferedReader (fr);

        String line;

        // initializing width, height, starting position 
        int xSTART = 0;
        int ySTART = 0;
        int width = 0;
        int height = 0;

        // initializing hashmap to store each coordiante and their living status
        Map <Coordinate, Boolean> grid = new LinkedHashMap<>();

        // reading the file
        while (null != (line = br.readLine())) {
            // extracting grid info
            if (line.startsWith("GRID")) {
                String [] gridInfo = line.split(" ");
                width = Integer.parseInt(gridInfo[1]);
                height = Integer.parseInt(gridInfo[2]);

                 // creating gridMap based on size specified
                grid = createMap (width, height);    
            }

            // extracting starting position
            if (line.startsWith("START")) {
                String [] startInfo = line.split(" ");
                xSTART = Integer.parseInt(startInfo[1]);
                ySTART = Integer.parseInt(startInfo[2]);
            }

            // reading data and indexing the position of * in grid
            if (line.startsWith("DATA")) {

                int y = ySTART;

                while (null != (line = br.readLine())) {
                    String [] lineArr = line.split("");

                    int x = xSTART;

                    for (int i = 0; i < lineArr.length; i++) {

                        if (lineArr[i].equals("*")) {
                            grid.put(new Coordinate (x, y), true);
                            // System.out.printf("x coor: %d, y coor: %d\n", x, y);
                        }
                        x+=1;
                    }
                    y+=1;
                }
                
            }

        }

        var updatedGrids = grid;

        // creating a list storing the hashmap result of each iteration
        List <Map> iterations = new ArrayList<>();

        // initialize the first grid
        iterations.add(updatedGrids);

        FileWriter fw = new FileWriter("./results/" + fileName + "_outcome");

         //keying the generations number
         int loopNum = 5;

        // running through the game
        for (int i = 0; i < loopNum; i++) {
            
            Map currGrid = iterations.get(i);
            Map gridClone = createMap (width, height);

            // System.out.println(grid);

            int generation = i + 1;

            fw.write("Iteration: " + generation);
            fw.write("\n");



            int population = 0;
        
            for (int yAxis = 0; yAxis < height; yAxis++) {
                for (int xAxis = 0; xAxis < width; xAxis++) {

                    int neighboursNum = 0;

                    //check if there is anyone on your right
                    if (currGrid.containsKey(new Coordinate (xAxis+1, yAxis))) {

                        var neighbourStatus = currGrid.get(new Coordinate (xAxis+1, yAxis));

                        if (neighbourStatus.equals(true)) {
                            neighboursNum += 1;
                        }
                        
                    }

                    //check if there is anyone on your left
                    if (currGrid.containsKey(new Coordinate (xAxis-1, yAxis))) {

                        var neighbourStatus = currGrid.get(new Coordinate (xAxis-1, yAxis));

                        if (neighbourStatus.equals(true)) {
                            neighboursNum += 1;
                        }
                        
                    }

                    //check if there is anyone on your top
                    if (currGrid.containsKey(new Coordinate (xAxis, yAxis-1))) {

                        var neighbourStatus = currGrid.get(new Coordinate (xAxis, yAxis-1));

                        if (neighbourStatus.equals(true)) {
                            neighboursNum += 1;
                        }
                        
                    }

                    //check if there is anyone below you
                    if (currGrid.containsKey(new Coordinate (xAxis, yAxis+1))) {

                        var neighbourStatus = currGrid.get(new Coordinate (xAxis, yAxis+1));

                        if (neighbourStatus.equals(true)) {
                            neighboursNum += 1;
                        }
                        
                    }

                    //check if there is anyone diagonally up right
                    if (currGrid.containsKey(new Coordinate (xAxis+1, yAxis-1))) {

                        var neighbourStatus = currGrid.get(new Coordinate (xAxis+1, yAxis-1));

                        if (neighbourStatus.equals(true)) {
                            neighboursNum += 1;
                        }
                        
                    }

                    //check if there is anyone diagonally up left
                    if (currGrid.containsKey(new Coordinate (xAxis-1, yAxis-1))) {

                        var neighbourStatus = currGrid.get(new Coordinate (xAxis-1, yAxis-1));

                        if (neighbourStatus.equals(true)) {
                            neighboursNum += 1;
                        }
                        
                    }

                    //check if there is anyone diagonally down right
                    if (currGrid.containsKey(new Coordinate (xAxis+1, yAxis+1))) {

                        var neighbourStatus = currGrid.get(new Coordinate (xAxis+1, yAxis+1));

                        if (neighbourStatus.equals(true)) {
                            neighboursNum += 1;
                        }
                        
                    }

                    //check if there is anyone diagonally down left
                    if (currGrid.containsKey(new Coordinate (xAxis-1, yAxis+1))) {

                        var neighbourStatus = currGrid.get(new Coordinate (xAxis-1, yAxis+1));

                        if (neighbourStatus.equals(true)) {
                            neighboursNum += 1;
                        }
                        
                    }

                    // calculating neighboursNum an update in the next iteration
                    if (neighboursNum <= 1) {
                        gridClone.put(new Coordinate (xAxis, yAxis), false);
                        // fw.write("0");
                        fw.write("0");

                    } else if (neighboursNum == 2 ) {
                        gridClone.put(new Coordinate (xAxis, yAxis), true);
                        // fw.write("0");
                        fw.write("*");
                        population += 1;

                    } else if (neighboursNum == 3 ) {
                        gridClone.put(new Coordinate (xAxis, yAxis), true);
                        // fw.write("0");
                        fw.write("*");
                        population += 1;

                    } else if (neighboursNum >= 4 ) {
                        gridClone.put(new Coordinate (xAxis, yAxis), false);
                        // fw.write("0");
                        fw.write("0");
    
                    }
                    System.out.printf("%d", neighboursNum);
                } 
                System.out.printf("\n");
                fw.write("\n");
            }
            iterations.add(gridClone);
            fw.write("\n");
            System.out.printf("\n");
            fw.write("Population: " + population);
            fw.write("\n");
            fw.write("\n");
            System.out.printf("\n");
            System.out.printf("\n");
            fw.flush();
        }
        fw.close();
    }
}