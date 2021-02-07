package class02;

public class Code06_CherryPickup {

	// 从matrix左上角，走到右下角，过程中只能向右或者向下
	// 到达后，回来，过程中只能向左或者向上，沿途数字只能获得一遍
	// 返回，最大路径和
	public static int comeGoMaxPathSum(int[][] matrix) {
		return process(matrix, 0, 0, 0);
	}

	// matrix中，没有负数
	// A来到的位置是 Ar,Ac
	// B来到的位置是 Br, Ar + Ac - Br
	// A和B，一定迈出的步数，一样多，同步走的
	// 两人会共同到达右下角，返回两个人路径的最大累加和
	// 重要限制：来到同一个位置时，只获得一份
	// 3 7 5 ?
	public static int process(int[][] matrix, int Ar, int Ac, int Br) {
		int N = matrix.length;
		int M = matrix[0].length;
		if (Ar == N - 1 && Ac == M - 1) {
			return matrix[Ar][Ac];
		}
		// 还没到右下角
		// A 下 B 右
		// A 下 B 下
		// A 右 B 右
		// A 右 B 下
		int Bc = Ar + Ac - Br;
		int ADownBRight = -1;
		if (Ar + 1 < N && Bc + 1 < M) {
			ADownBRight = process(matrix, Ar + 1, Ac, Br);
		}
		int ADownBDown = -1;
		if (Ar + 1 < N && Br + 1 < N) {
			ADownBDown = process(matrix, Ar + 1, Ac, Br + 1);
		}

		int ARightBRight = -1;
		if (Ac + 1 < M && Bc + 1 < M) {
			ARightBRight = process(matrix, Ar, Ac + 1, Br);
		}
		int ARightBDown = -1;
		if (Ac + 1 < M && Br + 1 < N) {
			ARightBDown = process(matrix, Ar, Ac + 1, Br + 1);
		}
		int nextBest = Math.max(Math.max(ADownBRight, ADownBDown), Math.max(ARightBRight, ARightBDown));
		// A B
		if (Ar == Br) {
			return matrix[Ar][Ac] + nextBest;
		}
		// A 和 B，一定是不同位置
		return matrix[Ar][Ac] + matrix[Br][Bc] + nextBest;
	}

	public static int cherryPickup1(int[][] grid) {
		int ans = process1(grid, 0, 0, 0);
		return ans < 0 ? 0 : ans;
	}

	public static int process1(int[][] grid, int x1, int y1, int x2) {
		if (x1 == grid.length || y1 == grid[0].length || x2 == grid.length || x1 + y1 - x2 == grid[0].length) {
			return Integer.MIN_VALUE;
		}

		if (x1 == grid.length - 1 && y1 == grid[0].length - 1) {
			return grid[x1][y1];
		}
		int next = Integer.MIN_VALUE;
		next = Math.max(next, process1(grid, x1 + 1, y1, x2 + 1));
		next = Math.max(next, process1(grid, x1 + 1, y1, x2));
		next = Math.max(next, process1(grid, x1, y1 + 1, x2));
		next = Math.max(next, process1(grid, x1, y1 + 1, x2 + 1));
		if (grid[x1][y1] == -1 || grid[x2][x1 + y1 - x2] == -1 || next == -1) {
			return -1;
		}
		if (x1 == x2) {
			return grid[x1][y1] + next;
		}
		return grid[x1][y1] + grid[x2][x1 + y1 - x2] + next;
	}

	public static int cherryPickup2(int[][] grid) {
		int N = grid.length;
		int M = grid[0].length;
		int[][][] dp = new int[N][M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < N; k++) {
					dp[i][j][k] = Integer.MIN_VALUE;
				}
			}
		}
		int ans = process2(grid, 0, 0, 0, dp);
		return ans < 0 ? 0 : ans;
	}

	public static int process2(int[][] grid, int x1, int y1, int x2, int[][][] dp) {
		if (x1 == grid.length || y1 == grid[0].length || x2 == grid.length || x1 + y1 - x2 == grid[0].length) {
			return Integer.MIN_VALUE;
		}
		if (dp[x1][y1][x2] != Integer.MIN_VALUE) {
			return dp[x1][y1][x2];
		}
		if (x1 == grid.length - 1 && y1 == grid[0].length - 1) {
			dp[x1][y1][x2] = grid[x1][y1];
			return dp[x1][y1][x2];
		}
		int next = Integer.MIN_VALUE;
		next = Math.max(next, process2(grid, x1 + 1, y1, x2 + 1, dp));
		next = Math.max(next, process2(grid, x1 + 1, y1, x2, dp));
		next = Math.max(next, process2(grid, x1, y1 + 1, x2, dp));
		next = Math.max(next, process2(grid, x1, y1 + 1, x2 + 1, dp));
		if (grid[x1][y1] == -1 || grid[x2][x1 + y1 - x2] == -1 || next == -1) {
			dp[x1][y1][x2] = -1;
			return dp[x1][y1][x2];
		}
		if (x1 == x2) {
			dp[x1][y1][x2] = grid[x1][y1] + next;
			return dp[x1][y1][x2];
		}
		dp[x1][y1][x2] = grid[x1][y1] + grid[x2][x1 + y1 - x2] + next;
		return dp[x1][y1][x2];
	}

	public static void main(String[] args) {
		int[][] matrix = { 
				{ 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 }, 
				{ 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 } };

		System.out.println(comeGoMaxPathSum(matrix));

		System.out.println(cherryPickup1(matrix));

		System.out.println(cherryPickup2(matrix));

	}

}
