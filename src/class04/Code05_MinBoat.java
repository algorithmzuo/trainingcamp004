package class04;

public class Code05_MinBoat {

	// 请保证arr有序
	public static int minBoat(int[] arr, int limit) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// Arrays.sort(arr);
		if(arr[arr.length - 1] > limit) {
			return -1;
		}
		int lessR = -1;
		// 所有的人体重都不超过limit，继续讨论
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] <= (limit / 2)) {
				lessR = i;
				break;
			}
		}
		if (lessR == -1) {
			return arr.length;
		}
		// 一定有左右两个部分，左(<= limit / 2) 右(> limit / 2)
		int L = lessR;
		int R = lessR + 1;
		int lessUnused = 0; // 画X的数量统计，画对号的量(加工出来的)
		while (L >= 0) {
			int solved = 0; // 此时的[L]，让R画过了几个数
			while (R < arr.length && arr[L] + arr[R] <= limit) {
				R++;
				solved++;
			}
			// R来到的位置是第一个开始又不达标的位置
			if (solved == 0) {
				lessUnused++;
				L--;
			} else { // 此时的[L]，让R画过了solved（>0）个数
				L = Math.max(-1, L - solved);
			}
		}
		int lessAll = lessR + 1;// 左半区总个数  <= limit /2 的区域
		int lessUsed = lessAll - lessUnused; // 画对号的量
		int moreUnsolved = arr.length - lessR - 1 - lessUsed; // > limit/2 区中，没搞定的数量
		return lessUsed + ((lessUnused + 1) >> 1) + moreUnsolved;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5 };
		int weight = 6;
		System.out.println(minBoat(arr, weight));
	}

}
