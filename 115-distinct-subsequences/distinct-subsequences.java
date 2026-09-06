class Solution {
    public int numDistinct(String s, String t) {
        int m = t.length();

        long[] dp = new long[m + 1];

        
        dp[0] = 1;

        for (char ch : s.toCharArray()) {
            for (int j = m; j >= 1; j--) {
                if (ch == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return (int) dp[m];
    }
}