package class04;

import java.util.HashMap;

public class Code02_MostEOR {

	public static int mostEOR(int[] arr) {
		int sum = 0;
		 // dp[i] -> arr[0..i]在最优划分的情况下，异或和为0最多的部分是多少个
		int[] dp = new int[arr.length];
		// key : 某个前缀异或和
		// value : 这个前缀异或和出现的最晚的结尾位置(0～value)
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) { // i所在的最后一块
			sum ^= arr[i]; // sum -> [0..i]所有数的异或和
			//  假设sum上次出现的结尾是k，0～k的异或和是sum，0～i的异或和也是sum，k+1~i是异或和等于0的
			// 前缀异或和出现的最晚的结尾位置,  k+1是离i最近的
			if (map.containsKey(sum)) { // 上一次，这个异或和出现的位置 
				// pre -> pre + 1 -> i , 最优划分，最后一个部分的开始位置，
				// (pre+1, i)最后一个部分
				int pre = map.get(sum); // sum    0..pre   (pre+1..i)
				dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
			}
			// dp[i]  = Max {  dp[i-1] ,   dp[k-1] + 1  }
			if (i > 0) {
				dp[i] = Math.max(dp[i - 1], dp[i]);
			}
			map.put(sum, i);
		}
		return dp[dp.length - 1];
	}

	// for test
	public static int comparator(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] eors = new int[arr.length];
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
			eors[i] = eor;
		}
		int[] mosts = new int[arr.length];
		mosts[0] = arr[0] == 0 ? 1 : 0;
		for (int i = 1; i < arr.length; i++) {
			mosts[i] = eors[i] == 0 ? 1 : 0;
			for (int j = 0; j < i; j++) {
				if ((eors[i] ^ eors[j]) == 0) {
					mosts[i] = Math.max(mosts[i], mosts[j] + 1);
				}
			}
			mosts[i] = Math.max(mosts[i], mosts[i - 1]);
		}
		return mosts[mosts.length - 1];
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 300;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int res = mostEOR(arr);
			int comp = comparator(arr);
			if (res != comp) {
				succeed = false;
				printArray(arr);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
