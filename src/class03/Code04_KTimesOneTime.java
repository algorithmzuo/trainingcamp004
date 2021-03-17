package class03;

// 这个代码废掉了，因为我写了一个更猛的
// 更猛的链接：https://github.com/algorithmzuo/algorithmbasic2020/blob/master/src/class02/Code03_KM.java
// 更猛的原因，因为上面链接里的代码解决了以下这个问题：
// 一个数组中，只有一个数出现k次，其他所有数出现m次，怎么找到这个出现了k次的数，已知k<m，要求时间复杂度O(N)
// 可以看到，本题就是更猛问题的一个特例，而且都是O(N)就很好了
// 讲解在“体系学习班第2节”
public class Code04_KTimesOneTime {

	public static int onceNum(int[] arr, int k) {
		int[] eO = new int[32];
		for (int i = 0; i != arr.length; i++) {
			// 当前数是arr[i], 请把arr[i]变成K进制的形式，每一位累加到eO
			setExclusiveOr(eO, arr[i], k);
		}
		int res = getNumFromKSysNum(eO, k);
		return res;
	}

	public static void setExclusiveOr(int[] eO, int value, int k) {
		int[] curKSysNum = getKSysNumFromNum(value, k);
		for (int i = 0; i != eO.length; i++) {
			eO[i] = (eO[i] + curKSysNum[i]) % k;
		}
	}

	public static int[] getKSysNumFromNum(int value, int k) {
		int[] res = new int[32];
		int index = 0;
		while (value != 0) {
			res[index++] = value % k;
			value = value / k;
		}
		return res;
	}

	public static int getNumFromKSysNum(int[] eO, int k) {
		int res = 0;
		for (int i = eO.length - 1; i != -1; i--) {
			res = res * k + eO[i];
		}
		return res;
	}

	public static void main(String[] args) {
		int[] test1 = { 1, 1, 1, 2, 6, 6, 2, 2, 10, 10, 10, 12, 12, 12, 6, 9 };
		System.out.println(onceNum(test1, 3));

		int[] test2 = { -1, -1, -1, -1, -1, 2, 2, 2, 4, 2, 2 };
		System.out.println(onceNum(test2, 5));

	}

}