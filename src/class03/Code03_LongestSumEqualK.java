package class03;

import java.util.HashMap;

public class Code03_LongestSumEqualK {

	public static class Node {
		int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static int ans = 0; // 收集累加和为K的，最长路径有多少个节点

	public static int longest(Node head, int K) {
		ans = 0;
		// key ： 前缀和
		// value : 该前缀和，最早出现在哪一层
		// sumMap：只维持，从头节点出发到当前节点，这条路径上的前缀和
		HashMap<Integer, Integer> sumMap = new HashMap<>();
		sumMap.put(0, -1);
		process(head, 0, 0, K, sumMap);
		return ans;
	}

	// 节点X在level层，从头节点到X的父节点形成的累加和是preSum，
	// 从头节点到X的路径上，每一个前缀和都存在sumMap里(key)，记录的是该前缀和最早出现的层数(value)
	// 求出必须以X节点结尾的、累加和是K的所有路径中，含有最多的节点是多少？
	// 并看看能不能更新全局的ans
	public static void process(Node X, int level, int preSum, int K, HashMap<Integer, Integer> sumMap) {
		if (X != null) {
			// 从头节点出发，到当前X节点，总的前缀和是多少，allSum
			int allSum = preSum + X.value;
			if (sumMap.containsKey(allSum - K)) {
				ans = Math.max(ans, level - sumMap.get(allSum - K));
			}
			if (!sumMap.containsKey(allSum)) {
				sumMap.put(allSum, level);
			}
			process(X.left, level + 1, allSum, K, sumMap);
			process(X.right, level + 1, allSum, K, sumMap);
			if (sumMap.get(allSum) == level) {
				sumMap.remove(allSum);
			}
		}
	}

	public static void main(String[] args) {
		//                   3
		//           -2             3
		//        1      4      5      -7
		//       3 5   2 -5  -5  -3   1   5
		int K = 0;
		Node head = new Node(3);
		head.left = new Node(-2);
		head.left.left = new Node(1);
		head.left.right = new Node(4);
		head.left.left.left = new Node(3);
		head.left.left.right = new Node(5);
		head.left.right.left = new Node(2);
		head.left.right.right = new Node(5);

		head.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(-7);
		head.right.left.left = new Node(-5);
		head.right.left.right = new Node(-3);
		head.right.right.left = new Node(1);
		head.right.right.right = new Node(5);

		System.out.println(longest(head, K));

	}

}
