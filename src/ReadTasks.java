/*
Class used to read tasks from a txt file
*/

import java.util.*;
import java.io.*;
import java.nio.file.*;

public class ReadTasks {
  public static void main(String[]args) throws Exception {
    //main used fot testing the methods
    matrixFromFile("u_s_hihi_512_16.txt");
  }
  
  private static ArrayList<String> splitInLines(String input_file) throws Exception {
    ArrayList<String> lines = new ArrayList<String>();
    int i = 0;
    
    for (String line : Files.readAllLines(Paths.get(input_file))) {
      lines.add(line);
      i++;
    }
    return lines;  
  }
  
  private static float[][] linesToMatrix(ArrayList<String> lines) {
    int lengthArray = lines.size();
    int machine = 0;
    int task = 0;
    float [][]matrixTasks = new float[512][16];
    
    for (String line : lines) {
      machine = 0;
      String temp = line.substring(1 + (line.indexOf(":")));
      
      for (String number : temp.split(",")) {
        //System.out.println(number.trim());
        matrixTasks[task][machine] = Float.parseFloat(number.trim());
        machine++;
      }
      task++;
    } 
    
    return matrixTasks;
  }
  
  public static float[][] matrixFromFile(String input_file) throws Exception {
    ArrayList<String> lines = splitInLines(input_file);
    return linesToMatrix(lines);
  }
  
}
