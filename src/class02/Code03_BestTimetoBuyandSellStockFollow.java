package class02;

public class Code03_BestTimetoBuyandSellStockFollow {

	public static int maxProfit(int K, int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int N = prices.length;
		if (K >= N / 2) {
			return allTrans(prices);
		}
		// dp一维表
		int[] dp = new int[N];
		int ans = 0;
		for (int tran = 1; tran <= K; tran++) {
			int pre = dp[0];
			int best = pre - prices[0];
			for (int index = 1; index < N; index++) {
				pre = dp[index];
				dp[index] = Math.max(dp[index - 1], prices[index] + best);
				best = Math.max(best, pre - prices[index]);
				ans = Math.max(dp[index], ans);
			}
		}
		return ans;
	}

	public static int allTrans(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int ans = 0;
		int min = prices[0];
		int max = prices[0];
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] >= prices[i - 1]) {
				max = prices[i];
			} else {
				ans += max - min;
				min = prices[i];
				max = prices[i];
			}
		}
		return ans + max - min;
	}

	public static int allTrans(int[] prices, int N) {
		int ans = 0;
		for (int i = 1; i < N; i++) {
			if (prices[i] > prices[i - 1]) {
				ans += prices[i] - prices[i - 1];
			}
		}
		return ans;
	}

	public static int maxMoney(int[] arr, int K) {
		if (K > arr.length / 2) {
			// 调用第二问的解，搞定
			return 1;
		}
		int N = arr.length;
		// dp[s][i] 指的是 arr[0..i]自由交易，但是交易次数不要超过s次，获得的最大钱数
		int[][] dp = new int[K + 1][N];

		for (int s = 1; s <= K; s++) {
			// s行
			// dp[s][1]
			// dp[s-1][0] - arr[0] + arr[1]     dp[s-1][1] - arr[1] + arr[1]

			
			int bestChoose = Math.max(
					dp[s-1][0] - arr[0],
					dp[s-1][1] - arr[1]);
			dp[s][1] = Math.max(dp[s][0], bestChoose + arr[1]);
			for (int i = 2; i < N; i++) {
				bestChoose = Math.max(bestChoose, dp[s-1][i] - arr[i]);
				dp[s][i] = Math.max(dp[s][i - 1], bestChoose + arr[i]);
			}
		}

		return dp[K][N - 1];
	}

}
