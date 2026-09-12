import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (x, y) -> x[1] != y[1]
                ? Integer.compare(x[1], y[1])
                : Integer.compare(x[3], y[3]));

        long[][] dp = new long[5][n + 1];
        int[][][] ans = new int[5][n + 1][];

        for (int k = 0; k <= 4; k++)
            Arrays.fill(ans[k], new int[0]);

        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= 4; k++) {
                dp[k][i] = dp[k][i - 1];
                ans[k][i] = ans[k][i - 1];

                int p = prev(a, i - 1);

                long score = dp[k - 1][p] + a[i - 1][2];

                int[] cur = Arrays.copyOf(ans[k - 1][p],
                        ans[k - 1][p].length + 1);

                cur[cur.length - 1] = a[i - 1][3];
                Arrays.sort(cur);

                if (score > dp[k][i] ||
                        (score == dp[k][i] && cmp(cur, ans[k][i]) < 0)) {
                    dp[k][i] = score;
                    ans[k][i] = cur;
                }
            }
        }

        return ans[4][n];
    }

    int prev(int[][] a, int i) {
        int l = 0, r = i - 1, res = 0;

        while (l <= r) {
            int m = (l + r) / 2;

            if (a[m][1] < a[i][0]) {
                res = m + 1;   // DP index
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return res;
    }

    int cmp(int[] a, int[] b) {
        for (int i = 0; i < Math.min(a.length, b.length); i++)
            if (a[i] != b[i])
                return Integer.compare(a[i], b[i]);

        return Integer.compare(a.length, b.length);
    }
}


