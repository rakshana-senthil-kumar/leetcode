class Solution {
    static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        // Answer = C(n + k - 1, 2 * k)
        int N = n + k - 1;
        int R = 2 * k;

        long[] dp = new long[R + 1];
        dp[0] = 1;

        // Calculate C(N, R) using 1D Pascal DP
        for (int i = 1; i <= N; i++) {
            for (int j = Math.min(i, R); j >= 1; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
            }
        }

        return (int) dp[R];
    }
}