import java.util.*;

public class l002_StringSet {

    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2d(int[][] dp) {
        for (int[] d : dp) {
            display(d);
        }
        System.out.println();
    }

    public static int lpss(String s, int si, int ei, int[][] dp) {
        if (si >= ei) {
            return dp[si][ei] = (si == ei) ? 1 : 0;
        }

        if (dp[si][ei] != 0) {
            return dp[si][ei];
        }
        if (s.charAt(si) != s.charAt(ei))
            dp[si][ei] = Math.max(lpss(s, si + 1, ei, dp), lpss(s, si, ei - 1, dp));
        else {
            dp[si][ei] = lpss(s, si + 1, ei - 1, dp);
        }
        return dp[si][ei];

    }

    public static int lpss_tabu(String s, int SI, int EI, int[][] dp) {
        int n = s.length();
        for (int gap = 0; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (si >= ei) {
                    dp[si][ei] = (si == ei) ? 1 : 0;
                    continue;
                }

                if (s.charAt(si) != s.charAt(ei))
                    dp[si][ei] = Math.max(dp[si + 1][ei], dp[si][ei - 1]);// lpss_tabu(s, si + 1, ei, dp), lpss_tabu(s,
                                                                          // si, ei - 1, dp));
                else {
                    dp[si][ei] = dp[si + 1][ei - 1] + 2; // lpss_tabu(s, si + 1, ei - 1, dp);
                }
            }
        }

        return dp[SI][EI];
    }

    public static String lpss_ReverseEngi(String s, int si, int ei, int[][] dp) {
        if (si >= ei) {
            return si == ei ? (s.charAt(si) + "") : "";
        }
        if (s.charAt(si) == s.charAt(ei))
            return s.charAt(si) + lpss_ReverseEngi(s, si + 1, ei - 1, dp) + s.charAt(ei);

        else if (dp[si + 1][ei] > dp[si][ei - 1])
            return lpss_ReverseEngi(s, si + 1, ei, dp);

        else
            return lpss_ReverseEngi(s, si, ei - 1, dp);
    }

    // public static String lpss_ReverseEngi(String s, int si, int ei, int[][] dp) {
    // if (si >= ei) {
    // return si == ei ? (s.charAt(si) + "") : "";
    // }

    // if (s.charAt(si) == s.charAt(ei))
    // return s.charAt(si) + lpss_ReverseEngi(s, si + 1, ei - 1, dp) + s.charAt(si);
    // else if (dp[si + 1][ei] > dp[si][ei - 1])
    // return lpss_ReverseEngi(s, si + 1, ei, dp);
    // else
    // return lpss_ReverseEngi(s, si, ei - 1, dp);
    // }

    public static void longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int ans = lpss_tabu(s, 0, n - 1, dp);

        display2d(dp);
        System.out.println(lpss_ReverseEngi(s, 0, n - 1, dp));
    }

    public int maximum(int... arr) {
        int max = arr[0];
        for (int ele : arr) {
            max = Math.max(max, ele);
        }
        return max;
    }

    // 1458
    public int maxDotProduct(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = -(int) 1e8;
        }

        if (dp[n][m] != -(int) 1e9)
            return dp[n][m];

        int val = nums1[n - 1] * nums2[m - 1];
        int acceptTwoNos = maxDotProduct(nums1, nums2, n - 1, m - 1, dp) + val;
        int a = maxDotProduct(nums1, nums2, n, m - 1, dp);
        int b = maxDotProduct(nums1, nums2, n - 1, m, dp);

        return dp[n][m] = maximum(acceptTwoNos, val, a, b);
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -(int) 1e9);
        }
        return maxDotProduct(nums1, nums2, n, m, dp);
    }

    // 72 EDIT DISTA
    public int minDistance(String word1, String word2, int n, int m, int[][] dp) {
        if (n == 0)
            return dp[n][m] = m; // insert
        else if (m == 0)
            return dp[n][m] = n; // delete

        if (dp[n][m] != -1)
            return dp[n][m];

        if (word1.charAt(n - 1) == word2.charAt(m - 1))
            return minDistance(word1, word2, n - 1, m - 1, dp);

        int insert = minDistance(word1, word2, n, m - 1, dp);
        int delete = minDistance(word1, word2, n - 1, m, dp);
        int replace = minDistance(word1, word2, n - 1, m - 1, dp);

        return dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        return minDistance(word1, word2, n, m, dp);
    }

    // 1058
    public int maxUncrossedLines(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0)
            return dp[n][m] = 0;

        if (dp[n][m] != -1) {
            return dp[n][m];
        }
        if (nums1[n - 1] == nums2[m - 1])
            return dp[n][m] = maxUncrossedLines(nums1, nums2, n - 1, m - 1, dp) + 1;
        else
            return dp[n][m] = Math.max(maxUncrossedLines(nums1, nums2, n, m - 1, dp),
                    maxUncrossedLines(nums1, nums2, n - 1, m, dp));

    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        return maxUncrossedLines(nums1, nums2, n, m, dp);
    }

    // 115 leetcode
    public int numDistinct(String s, String t, int n, int m, int[][] dp) {
        if (n == 0 || m == 0 || n < m)
            return dp[n][m] = (m == 0 ? 1 : 0);

        if (dp[n][m] != -1)
            return dp[n][m];

        int a = numDistinct(s, t, n - 1, m - 1, dp);
        int b = numDistinct(s, t, n - 1, m, dp);

        if (s.charAt(n - 1) == t.charAt(m - 1))
            return dp[n][m] = a + b;
        else
            return dp[n][m] = b;

    }

    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        return numDistinct(s, t, n, m, dp);
    }

    public static void main(String[] args) {
        longestPalindromeSubseq("abcdbbcefgaaa");
    }
}
