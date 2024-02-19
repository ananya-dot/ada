package org.example;

public class MaxProfit {
    public static int maxProfitSpotPrice(int[][] prices, int n, int m) {
        int[][] dp = new int[n+ 1][m + 1];
        for (int height = 1; height <= n; height++) {
            for (int width = 1; width <= m; width++) {
                for (int i = 1; i < height; i++) {
                    dp[height][width] = Math.max(dp[height][width], dp[i][width] + dp[height - i][width]);
                }
                for (int j = 1; j < width; j++) {
                    dp[height][width] = Math.max(dp[height][width], dp[height][j] + dp[height][width - j]);
                }

                dp[height][width] = Math.max(dp[height][width], prices[height- 1][width - 1]);
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        int[][] prices = {{2, 4, 1}, {4, 1, 3}, {100, 90, 10}
        };
        int m = 3;
        int n = 3;

        System.out.println(maxProfitSpotPrice(prices, n, m));
    }
}
