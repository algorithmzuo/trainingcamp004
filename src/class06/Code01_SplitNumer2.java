package class06;

public class Code01_SplitNumer2 {
	
	public static int ways1(int n) {
		if (n < 1) {
			return 0;
		}
		return process(1, n);
	}

	// pre  要裂开rest的，前一个约束(rest裂开的第一个部分不能<pre)
	// rest 还剩多少值，需要去裂开
	// 返回裂开的方法数
	public static int process(int pre, int rest) {
		if (rest == 0) {
			return 1; // 之前裂开的方案，构成了1种有效方法
		}
		// 如果rest还剩下东西
		if (pre > rest) {
			return 0;
		}
		int ways = 0;
		for (int i = pre; i <= rest; i++) { // i : rest第一个裂开的部分，值是多少
			ways += process(i, rest - i);
		}
		return ways;
	}
	
	public static int ways1dp(int n) {
		if (n < 1) {
			return 0;
		}
		// pre -> 0  ~ n  (0不用)
		// rest -> 0 ~ n
		int[][] dp = new int[n+1][n+1];
		// dp[0][...]不需要填
		for(int pre = 1; pre <= n; pre++) {
			dp[pre][0] = 1;
		}
		for(int pre = n; pre >= 1; pre--) {
			for(int rest = pre; rest <= n; rest++) {
				// dp[pre][rest]
				int ways = 0;
				for (int i = pre; i <= rest; i++) { // i : rest第一个裂开的部分，值是多少
					ways += dp[i] [rest - i];
				}
				dp[pre][rest] = ways;
			}
		}
		return dp[1][n];
	}
	
	
	
	
	
	

	public static int ways2(int n) {
		if (n < 1) {
			return 0;
		}
		int[][] dp = new int[n + 1][n + 1];
		for (int pre = 1; pre < dp.length; pre++) {
			dp[pre][0] = 1;
		}
		for (int pre = n; pre > 0; pre--) {
			for (int rest = pre; rest <= n; rest++) {
				for (int i = pre; i <= rest; i++) {
					dp[pre][rest] += dp[i][rest - i];
				}
			}
		}
		return dp[1][n];
	}

	public static int ways3(int n) {
		if (n < 1) {
			return 0;
		}
		int[][] dp = new int[n + 1][n + 1];
		for (int pre = 1; pre < dp.length; pre++) {
			dp[pre][0] = 1;
		}
		for (int pre = 1; pre < dp.length; pre++) {
			dp[pre][pre] = 1;
		}
		for (int pre = n - 1; pre > 0; pre--) {
			for (int rest = pre + 1; rest <= n; rest++) {
				dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
			}
		}
		return dp[1][n];
	}

	public static void main(String[] args) {
		int n = 20;
		System.out.println(ways1(n));
		System.out.println(ways1dp(n));
		System.out.println(ways2(n));
		System.out.println(ways3(n));
	}

}
