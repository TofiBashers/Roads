/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author TofixXx
 */
public class Roads {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader breader = new BufferedReader(new FileReader("input.txt"));
        int N, M, K;
        String nums[] = breader.readLine().split(" ");
        N = Integer.parseInt(nums[0]);
        M = Integer.parseInt(nums[1]);
        K = Integer.parseInt(nums[2]);
        UFRoads roads = new UFRoads(N);
        while(M != 0)
        {
            String edge[] = breader.readLine().split(" ");
            roads.Union(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
            M--;
        }
        while(K != 0)
        {
            String edge[] = breader.readLine().split(" ");
            roads.delete(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
            System.out.println(roads.SetsNum-1);
            K--;
        }
    }
}
