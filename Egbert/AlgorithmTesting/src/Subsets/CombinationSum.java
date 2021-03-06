package Subsets;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * @leetcode https://leetcode.com/problems/combination-sum
 * @date 24/7/2019
 * @Time target/min(candi)  ^ length
 * @Space length
 */
public class CombinationSum {
    // Solution 1: Typical DFS N-nodes tree
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        List<Integer> currLayer = new ArrayList<>();
        combinDFS(candidates, target, 0, currLayer, result);
        return result;
    }
    private static void combinDFS(int[] candidates, int targetLeft,
                                  int level, List<Integer> currLayer,
                                  List<List<Integer>> result) {
        if (level == candidates.length) {
            if (targetLeft == 0) {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < currLayer.size(); i++) {
                    int n = currLayer.get(i);
                    for (int j = 0; j < n; j++) {
                        list.add(candidates[i]);
                    }
                }
                result.add(list);
            }
            return;
        }
        int maxNum = targetLeft / candidates[level];
        for (int i = 0; i <= maxNum; i++) {
            currLayer.add(i);
            combinDFS(candidates, targetLeft - candidates[level] * i,
                    level + 1, currLayer, result);
            currLayer.remove(currLayer.size() - 1);
        }
    }

    // Solution 2: DFS General DFS approach
    public static List<List<Integer>> combinationSumGDFS(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        List<Integer> currLayer = new ArrayList<>();
        Arrays.sort(nums);
        GDFS(nums, 0, target, currLayer, result);
        return result;
    }
    private static void GDFS(int[] nums, int currLevel, int target,
                             List<Integer> currLayer,
                             List<List<Integer>> result) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            result.add(new ArrayList<>(currLayer));
        } else {
            for (int i = currLevel; i < nums.length; i++) {
                currLayer.add(nums[i]);
                // not currLevel + 1, becasue we can reuse same elements
                GDFS(nums, i, target - nums[i], currLayer, result);
                currLayer.remove(currLayer.size() - 1);
            }
        }
    }
    public static void main(String[] args) {
        int[] input1 = new int[]{2,3,6,7};
        System.out.println(CombinationSum.combinationSum(input1, 7).toString());

        int[] input2 = new int[]{2,3,5};
        System.out.println(CombinationSum.combinationSum(input2, 67).toString());

        int[] input3 = new int[0];
        System.out.println(CombinationSum.combinationSum(input3, 8).toString());
    }
}
