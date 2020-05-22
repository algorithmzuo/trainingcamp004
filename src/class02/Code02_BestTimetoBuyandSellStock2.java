package class02;

public class Code02_BestTimetoBuyandSellStock2 {

	public int maxProfit(int[] prices) {
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

}
