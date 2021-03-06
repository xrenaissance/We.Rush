package SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @link https://www.educative.io/collection/page/5668639101419520/5671464854355968/4739274459054080
 * @link https://leetcode.com/problems/minimum-window-substring/
 * @author Egbert Li
 * @date 22/05/2019
 * @Time O M + N
 * @Space O M
 */
public class MinimumWindowSubstring {
    public static String findPermutation(String str, String pattern) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int windowStart = 0;
        int solDistance = str.length() + 1;
        int solStart = 0;
        int matched = 0;
        Map<Character, Integer> charFreqMap = StringAnagrams.createCharFreqMap(pattern);
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (charFreqMap.containsKey(rightChar)) {
                charFreqMap.put(rightChar, charFreqMap.get(rightChar) - 1);
                if (charFreqMap.get(rightChar) >= 0) {
                    matched++;
                }
            }
            while (matched == pattern.length()) {
                //System.out.println("matched!");
                if (solDistance > windowEnd - windowStart + 1) {
                    solStart = windowStart;
                    solDistance = windowEnd - windowStart + 1;
                }
                char leftChar = str.charAt(windowStart++);
                if (charFreqMap.containsKey(leftChar)) {
                    if (charFreqMap.get(leftChar) == 0) {
                        matched--;
                        System.out.println("matched decreases!");
                    }
                    charFreqMap.put(leftChar, charFreqMap.get(leftChar) + 1);
                }
            }
        }
        return solDistance > str.length() ? "" :
                             str.substring(solStart, solStart + solDistance);
    }

    public static void main(String[] args) {
        //System.out.println(MinimumWindowSubstring.findPermutation("aabdec", "abc"));
        //System.out.println(MinimumWindowSubstring.findPermutation("abdabca", "abc"));
        //System.out.println(MinimumWindowSubstring.findPermutation("adcad", "abc"));
        System.out.println(MinimumWindowSubstring.findPermutation("aa", "aa"));
    }
}
