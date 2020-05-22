package class02;

public class Code06_CherryPickup {

	public static int cherryPickup1(int[][] grid) {
		int ans = process1(grid, 0, 0, 0);
		return ans < 0 ? 0 : ans;
	}

	public static int process1(int[][] grid, int x1, int y1, int x2) {
		if (x1 == grid.length || y1 == grid.length || x2 == grid.length || x1 + y1 - x2 == grid.length) {
			return Integer.MIN_VALUE;
		}

		if (x1 == grid.length - 1 && y1 == grid.length - 1) {
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
		int[][][] dp = new int[N][N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					dp[i][j][k] = Integer.MIN_VALUE;
				}
			}
		}
		int ans = process2(grid, 0, 0, 0, dp);
		return ans < 0 ? 0 : ans;
	}

	public static int process2(int[][] grid, int x1, int y1, int x2, int[][][] dp) {
		if (x1 == grid.length || y1 == grid.length || x2 == grid.length || x1 + y1 - x2 == grid.length) {
			return Integer.MIN_VALUE;
		}

		if (dp[x1][y1][x2] != Integer.MIN_VALUE) {
			return dp[x1][y1][x2];
		}

		if (x1 == grid.length - 1 && y1 == grid.length - 1) {
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

}
