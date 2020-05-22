package class03;

public class Code03_BinaryTreeMaximumPathSum {

	public class Node {
		int value;
		public Node left;
		public Node right;
	}

	public static class ReturnData {
		public int maxPathSum;// 整棵树的最大路径和
		public int headMaxPathSum; // 必须从头节点出发的情况下的最大路径和

		public ReturnData(int m, int h) {
			maxPathSum = m;
			headMaxPathSum = h;
		}
	}

	public static ReturnData process(Node x) {
		if (x == null) {
			return null;
		}
		ReturnData leftData = process(x.left);
		ReturnData rightData = process(x.right);
		// 和x无关的时候
		int maxPathSumP1 = Integer.MIN_VALUE;
		if (leftData != null) {
			maxPathSumP1 = Math.max(maxPathSumP1, leftData.maxPathSum);
		}
		if (rightData != null) {
			maxPathSumP1 = Math.max(maxPathSumP1, rightData.maxPathSum);
		}

		// 和x有关的时候 情况二
		int maxPathSumP2 = x.value;
		
		
		// x决定往左走获得的最好情况
		int maxPathSumP3 = leftData != null ? x.value + leftData.headMaxPathSum 
				: Integer.MIN_VALUE;
		
		// x决定往右走获得的最好情况
		int maxPathSumP4 = rightData != null 
				? x.value + rightData.headMaxPathSum 
						: Integer.MIN_VALUE;
		
		
		int maxPathSumP5 = leftData != null && rightData != null
				? x.value + leftData.headMaxPathSum + rightData.headMaxPathSum
				: Integer.MIN_VALUE;
		int maxPathSum = Math.max(Math.max(maxPathSumP1, Math.max(maxPathSumP2, maxPathSumP3)),
				Math.max(maxPathSumP4, maxPathSumP5));
		int headMaxPathSum = Math.max(maxPathSumP2, Math.max(maxPathSumP3, maxPathSumP4));

		return new ReturnData(maxPathSum, headMaxPathSum);
	}

	//
	// public static int maxPathSum(Node head) {
	// if (head == null) {
	// return 0;
	// }
	// ReturnType allData = process(head);
	// return allData.maxValue < 0 ? allData.maxValue : allData.maxSumAll;
	// }
	//
	// public static class ReturnType {
	// public int maxSumAll;
	// public int maxSumHead;
	// public int maxValue;
	//
	// public ReturnType(int all, int fromHead, int max) {
	// maxSumAll = all;
	// maxSumHead = fromHead;
	// maxValue = max;
	// }
	// }
	//
	// public static ReturnType process(Node x) {
	// if (x == null) {
	// return new ReturnType(0, 0, Integer.MIN_VALUE);
	// }
	// ReturnType leftData = process(x.left);
	// ReturnType rightData = process(x.right);
	//
	//
	//
	// int maxValue = Math.max(x.value,
	// Math.max(leftData.maxValue, rightData.maxValue));
	//
	//
	//
	// int maxSumHead = Math.max(0,
	// Math.max(leftData.maxSumHead,
	// rightData.maxSumHead)
	// )
	//
	// + x.value;
	//
	//
	// // 和x无关情况下的答案
	// int maxSumAll = Math.max(leftData.maxSumAll, rightData.maxSumAll);
	//
	//
	//
	// maxSumAll = Math.max(maxSumAll,
	// Math.max(0, leftData.maxSumHead)
	// + Math.max(0, rightData.maxSumHead)
	// + x.value);
	//
	//
	//
	// return new ReturnType(maxSumAll, maxSumHead, maxValue);
	// }

}
