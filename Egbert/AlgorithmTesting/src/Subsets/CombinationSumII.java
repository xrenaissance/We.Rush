package Subsets;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * @leetcode https://leetcode.com/problems/combination-sum-ii/
 * @date 23/7/2019
 * @Time 2 ^ n worst
 * @Space n
 */
public class CombinationSumII {
    // Solution 1: get all combination, and get one which have target pair
    // not efficient
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        List<Integer> currLayer = new ArrayList<>();
        Arrays.sort(candidates);
        combinDFS(candidates, target, 0, currLayer, result);
        return result;
    }
    private static void combinDFS(int[] candidates, int target, int level,
                                  List<Integer> currLayer,
                                  List<List<Integer>> result) {
        if (level == candidates.length) {
            if (target == 0) {
                result.add(new ArrayList<>(currLayer));
            }
            return;
        }
        // add num in current level
        currLayer.add(candidates[level]);
        combinDFS(candidates, target - candidates[level],
                  level + 1, currLayer, result);
        currLayer.remove(currLayer.size() - 1);

        // don't add num in current level and skip repeated num
        while (level + 1 < candidates.length &&
               candidates[level] == candidates[level + 1]) {
            level++;
        }
        combinDFS(candidates, target, level + 1, currLayer, result);
    }
    // Using General DFS pattern
    public static List<List<Integer>> combinationSum2GDFS(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        List<Integer> currLayer = new ArrayList<>();
        Arrays.sort(candidates);
        GDFS(candidates, currLayer, 0, target, result);
        return result;
    }
    private static void GDFS(int[] candidates, List<Integer> currLayer,
                             int currLevel, int target,
                             List<List<Integer>> result) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            result.add(new ArrayList<>(currLayer));
        } else {
            for (int i = currLevel; i < candidates.length; i++) {
                if (i > currLevel && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                currLayer.add(candidates[i]);
                GDFS(candidates, currLayer, i + 1,
                        target - candidates[i], result);
                currLayer.remove(currLayer.size() - 1);
            }
        }
    }
    public static void main(String[] args) {
        int[] input1 = new int[]{10,1,2,7,6,1,5};
        System.out.println(CombinationSumII.combinationSum2GDFS(input1, 8));

        int[] input2 = new int[]{2,5,2,1,2};
        System.out.println(CombinationSumII.combinationSum2GDFS(input2, 5));

        int[] input3 = new int[] {14,6,25,9,30,20,33,34,28,30,16,12,31,9,9,12,34,16,25,32,8,7,30,12,33,20,21,29,24,17,27,34,11,17,30,6,32,21,27,17,16,8,24,12,12,28,11,33,10,32,22,13,34,18,12};
        System.out.println(CombinationSumII.combinationSum2GDFS(input3, 27));
    }
}
