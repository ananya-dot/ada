package org.example;

public class MaxProfit {
    public static int maxProfitSpotPrice(int[][] prices, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int width = 1; width <= m; width++) {
            for (int height = 1; height <= n; height++) {
                for (int i = 1; i < width; i++) {
                    dp[width][height] = Math.max(dp[width][height], dp[i][height] + dp[width - i][height]);
                }
                for (int j = 1; j < height; j++) {
                    dp[width][height] = Math.max(dp[width][height], dp[width][j] + dp[width][height - j]);
                }

                dp[width][height] = Math.max(dp[width][height], prices[width - 1][height - 1]);
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        int[][] prices = {{2, 4, 1}, {4, 1, 3}, {100, 90, 10}
        };
        int m = 3;
        int n = 3;

        System.out.println(maxProfitSpotPrice(prices, m, n));
    }
}
