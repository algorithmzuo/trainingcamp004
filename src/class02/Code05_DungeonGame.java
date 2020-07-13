package class02;

public class Code05_DungeonGame {

	public static int needMin(int[][] matrix) {
		return process(matrix, matrix.length, matrix[0].length, 0, 0);
	}

	// 来到了matrix[row][col]，还没登上去，到达右下角，返回至少的初始血量
	public static int process(int[][] matrix, int N, int M, int row, int col) {
		if(row == N - 1 && col == M - 1) { // 已经达到右下角了
			return matrix[N-1][M-1] < 0 ? (-matrix[N-1][M-1] + 1) : 1;
		}
		if(row == N - 1) {
			int rightNeed = process(matrix, N, M, row, col+1);
			if(matrix[row][col] < 0) { // 3    -7    10
				return -matrix[row][col] + rightNeed;
			}else if(matrix[row][col] >= rightNeed) {  // 3    3    1
				return 1;
			}else { //  3   1    2
				return rightNeed - matrix[row][col];
			}
		}
		if(col == M - 1) {
			int downNeed = process(matrix, N, M, row+1, col);
			if(matrix[row][col] < 0) { // 3    -7    10
				return -matrix[row][col] + downNeed;
			}else if(matrix[row][col] >= downNeed) {  // 3    3    1
				return 1;
			}else { //  3   1    2
				return downNeed - matrix[row][col];
			}
		}
		int minNextNeed = Math.min(process(matrix, N, M, row, col+1), process(matrix, N, M, row+1, col));
		if(matrix[row][col] < 0) { // 3    -7    10
			return -matrix[row][col] + minNextNeed;
		}else if(matrix[row][col] >= minNextNeed) {  // 3    3    1
			return 1;
		}else { //  3   1    2
			return minNextNeed - matrix[row][col];
		}
	}

	public static int minHP1(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 1;
		}
		int row = m.length;
		int col = m[0].length;
		int[][] dp = new int[row--][col--];
		dp[row][col] = m[row][col] > 0 ? 1 : -m[row][col] + 1;
		for (int j = col - 1; j >= 0; j--) {
			dp[row][j] = Math.max(dp[row][j + 1] - m[row][j], 1);
		}
		int right = 0;
		int down = 0;
		for (int i = row - 1; i >= 0; i--) {
			dp[i][col] = Math.max(dp[i + 1][col] - m[i][col], 1);
			for (int j = col - 1; j >= 0; j--) {
				right = Math.max(dp[i][j + 1] - m[i][j], 1);
				down = Math.max(dp[i + 1][j] - m[i][j], 1);
				dp[i][j] = Math.min(right, down);
			}
		}
		return dp[0][0];
	}

	public static int minHP2(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 1;
		}
		int more = Math.max(m.length, m[0].length);
		int less = Math.min(m.length, m[0].length);
		boolean rowmore = more == m.length;
		int[] dp = new int[less];
		int tmp = m[m.length - 1][m[0].length - 1];
		dp[less - 1] = tmp > 0 ? 1 : -tmp + 1;
		int row = 0;
		int col = 0;
		for (int j = less - 2; j >= 0; j--) {
			row = rowmore ? more - 1 : j;
			col = rowmore ? j : more - 1;
			dp[j] = Math.max(dp[j + 1] - m[row][col], 1);
		}
		int choosen1 = 0;
		int choosen2 = 0;
		for (int i = more - 2; i >= 0; i--) {
			row = rowmore ? i : less - 1;
			col = rowmore ? less - 1 : i;
			dp[less - 1] = Math.max(dp[less - 1] - m[row][col], 1);
			for (int j = less - 2; j >= 0; j--) {
				row = rowmore ? i : j;
				col = rowmore ? j : i;
				choosen1 = Math.max(dp[j] - m[row][col], 1);
				choosen2 = Math.max(dp[j + 1] - m[row][col], 1);
				dp[j] = Math.min(choosen1, choosen2);
			}
		}
		return dp[0];
	}

	public static void main(String[] args) {
		int[][] map = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 }, };
		System.out.println(minHP1(map));
		System.out.println(minHP2(map));

	}

}
