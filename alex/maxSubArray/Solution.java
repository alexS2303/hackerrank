import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                List<Integer> result = Result.maxSubarray(arr);

                bufferedWriter.write(
                    result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                    + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}

class Result {

    /*
     * Complete the 'maxSubarray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static List<Integer> maxSubarray(List<Integer> arr) {
    // Write your code here
        
        
        if (arr.isEmpty()) {
            return Arrays.asList(0, 0);
        }
        
        int pSum = Math.max(0, arr.get(0));
        int curCSum = Math.max(0, arr.get(0));
        int maxCSum = curCSum;
        boolean onlyNegative = (arr.get(0) < 0);
        int lowestNegative = Math.min(arr.get(0), 0);
        
        for (int i = 1; i < arr.size(); i++) {
            // System.out.println("Iteration: " + i);
            int curNum = arr.get(i);
            if (curNum > 0) {
                pSum += curNum;
                onlyNegative = false;
            } else if (onlyNegative && curNum > lowestNegative) {
              lowestNegative = curNum;  
            }
            
            // System.out.println("CurNum: " + curNum);
            
            int diff = curCSum + curNum;
            
            // System.out.println("Diff: " + diff);
            if (diff < 0) {
                curCSum = 0;
            } else {
                curCSum = diff;
                if (curCSum > maxCSum) {
                    maxCSum = curCSum;
                }
            }
            
            // System.out.println("CurSum: " + curCSum);
            // System.out.println("MaxSum: " + maxCSum);
        }
        
           
        return (onlyNegative) ? Arrays.asList(lowestNegative, lowestNegative) : Arrays.asList(maxCSum, pSum);
    }
}