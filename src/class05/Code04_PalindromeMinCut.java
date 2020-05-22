package class05;

public class Code04_PalindromeMinCut {

	public static int minCut(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		char[] chas = str.toCharArray();
		int len = chas.length;
		int[] dp = new int[len + 1];
		dp[len] = 0;
		dp[len-1] = 1;
		boolean[][] p = record(chas);
		for (int i = len - 2; i >= 0; i--) {
			dp[i] = chas.length - i;
			for (int j = i; j < len; j++) { // i..j
				if (p[i][j]) {
					dp[i] = Math.min(dp[i], dp[j + 1] + 1);
				}
			}
		}
		return dp[0];
	}

	public static boolean[][] record(char[] str) {
		boolean[][] record = new boolean[str.length][str.length];
		record[str.length - 1][str.length - 1] = true;
		for (int i = 0; i < str.length - 1; i++) {
			record[i][i] = true;
			record[i][i + 1] = str[i] == str[i + 1];
		}
		for (int row = str.length - 3; row >= 0; row--) {
			for (int col = row + 2; col < str.length; col++) {
				record[row][col] = str[row] == str[col] && record[row + 1][col - 1];
			}
		}
		return record;
	}

	// for test
	public static String getRandomStringOnlyAToD(int len) {
		int range = 'D' - 'A' + 1;
		char[] charArr = new char[(int) (Math.random() * (len + 1))];
		for (int i = 0; i != charArr.length; i++) {
			charArr[i] = (char) ((int) (Math.random() * range) + 'A');
		}
		return String.valueOf(charArr);
	}

	public static void main(String[] args) {
		int maxLen = 10;
		int testTimes = 5;
		String str = null;
		for (int i = 0; i != testTimes; i++) {
			str = getRandomStringOnlyAToD(maxLen);
			System.out.print("\"" + str + "\"" + " : ");
			System.out.println(minCut(str));
		}

	}
}
