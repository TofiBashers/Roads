
package roads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author TofixXx
 */

/** Solving a Roads task by using Disjoint-Set Union structure*/
public class Roads {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
                FileWriter writer = new FileWriter("output.txt")) {
            int N, M, K;
            String nums[] = reader.readLine().split(" ");
            N = Integer.parseInt(nums[0]);
            M = Integer.parseInt(nums[1]);
            K = Integer.parseInt(nums[2]);
            DisjointSetUnion roads = new DisjointSetUnion(N);
            while (M != 0) {
                String[] road = reader.readLine().split(" ");
                roads.union(Integer.parseInt(road[0]), Integer.parseInt(road[1]));
                M--;
            }
            while (K != 0) {
                String[] road = reader.readLine().split(" ");
                roads.delete(Integer.parseInt(road[0]), Integer.parseInt(road[1]));
                writer.write(Integer.toString(roads.getSetsNum() - 1)
                        + System.getProperty("line.separator"));
                K--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
