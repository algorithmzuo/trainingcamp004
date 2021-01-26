package class06;

public class Code02_KInversePairs2 {

	public static int dp1(int N, int K) {
		if (N < 1 || K < 0) {
			return 0;
		}
		if (K == 0) {
			return 1;
		}
		int[][] dp = new int[N + 1][K + 1];
		dp[1][0] = 1;
		for (int i = 2; i <= N; i++) {
			dp[i][0] = 1;
		}
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				for (int s = j; s >= Math.max(0, j - i + 1); s--) {
					dp[i][j] += dp[i - 1][s];
				}
			}
		}
		return dp[N][K];
	}

	public static int dp2(int N, int K) {
		if (N < 1 || K < 0) {
			return 0;
		}
		if (K == 0) {
			return 1;
		}
		int[][] dp = new int[N + 1][K + 1];
		// dp[0][...] 不要
		dp[1][0] = 1;// dp[1][1...] 0
		for (int i = 2; i <= N; i++) {
			dp[i][0] = 1;
		}
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				// dp[i][j] -> dp[i][j-1]
				// j == 1 dp[i][1] dp[i][0]
//				if (i > j) {
//					dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
//				}
//				if (i <= j) {
//					dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - i];
//				}
				dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - (i <= j ? dp[i - 1][j - i] : 0);
			}
		}
		return dp[N][K];
	}

	public static int kInversePairs1(int n, int k) {
		if (n < 1 || k < 0) {
			return 0;
		}
		int[][] dp = new int[n + 1][k + 1];
		dp[0][0] = 1;
		int mod = 1000000007;
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 1;
			for (int p = 1; p <= k; p++) {
				for (int r = Math.max(0, p - i + 1); r <= p; r++) {
					dp[i][p] += dp[i - 1][r];
					dp[i][p] %= mod;
				}
			}
		}
		return dp[n][k];
	}

	public static int kInversePairs2(int n, int k) {
		if (n < 1 || k < 0) {
			return 0;
		}
		int[][] dp = new int[n + 1][k + 1];
		dp[0][0] = 1;
		int mod = 1000000007;
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 1;
			for (int p = 1; p <= k; p++) {
				dp[i][p] = (dp[i][p - 1] + dp[i - 1][p]) % mod;
				if (p >= i) {
					dp[i][p] = (dp[i][p] - dp[i - 1][p - i] + mod) % mod;
				}
			}
		}
		return dp[n][k];
	}

	public static void main(String[] args) {
		int N = 9;
		int K = 15;
		System.out.println(dp1(N, K));
		System.out.println(dp2(N, K));
		System.out.println(kInversePairs2(N, K));
	}

}
