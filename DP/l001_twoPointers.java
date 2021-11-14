import java.util.*;

public class l001_twoPointers {
    /*
     * 1. Faith(comment me likhege) 2. Recursive Tree 3. recursion code 4. convert
     * it in memoizaton 5. observation 6. tabulation 7 optimization if possible
     */

    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2d(int[][] dp) {
        for (int[] ar : dp) {
            display(ar);
        }
        System.out.println();
    }
    // faith = f(n) = f(n-1) + f(n-2);
    // public static int fibo_memo(int n, int[] dp){

    // if()
    // }
    public static int divideinKGroups_memo(int n, int k, int[][] dp) {
        if (k == 1 || n == k) {
            return dp[n][k] = 1;
        }
        if (dp[n][k] != -1) {
            return dp[n][k];
        }

        int selfSet = divideinKGroups_memo(n - 1, k - 1, dp);
        int partsOfAnotherSet = divideinKGroups_memo(n - 1, k, dp) * k;

        return dp[n][k] = selfSet + partsOfAnotherSet;
    }

    public static int divideinKGroups_Tabu(int N, int K, int[][] dp) {
        for (int n = 1; n <= K; n++) {
            for (int k = 1; k <= N; k++) {
                if (k > n) {
                    break;
                }
                if (k == 1 || n == k) {
                    dp[n][k] = 1;
                    continue;
                }
                int selfSet = divideinKGroups_Tabu(n - 1, k - 1, dp);
                int partsOfAnotherSet = divideinKGroups_Tabu(n - 1, k, dp) * k;

                dp[n][k] = selfSet + partsOfAnotherSet;
            }
        }
        return dp[N][K];
    }

    public static void divideinKGroups() {
        int n = 5, k = 3;
        int[][] dp = new int[n + 1][k + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        // System.out.println(divideinKGroups_memo(n, k, dp));
        System.out.println(divideinKGroups_Tabu(5, 3, dp));
        display2d(dp);
    }

    public static void main(String[] args) {
        divideinKGroups();
    }

}