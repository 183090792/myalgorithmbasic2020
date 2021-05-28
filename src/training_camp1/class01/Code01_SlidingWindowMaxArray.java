package training_camp1.class01;

import java.util.LinkedList;

/**
 * 窗口、首尾指针法，
 * 题目：
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出窗口的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 */

/**
 * 什么是滑动窗口？
 * 滑动窗口是一种想象出来的数据结构：
 * 滑动窗口有左边界L和有边界R
 * 在数组或者字符串或者一个序列上，记为S，窗口就是S[L..R]这一部分
 * L往右滑意味着一个样本出了窗口，R往右滑意味着一个样本进了窗口
 * L和R都只能往右滑
 *
 * 滑动窗口能做什么？
 * 滑动窗口、首尾指针等技巧，说白了是一种求解问题的流程设计
 *
 * 滑动内最大值和最小值的更新结构
 * 窗口不管L还是R滑动之后，都会让窗口呈现新状况，
 * 如何能够更快的得到窗口当前状况下的最大值和最小值？
 * 最好平均下来复杂度能做到 O(1)
 * 利用单调双端队列！
 */
public class Code01_SlidingWindowMaxArray {

	public static int[] getMaxWindow(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		// 其中放的是位置，头代表 （大->小）尾
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		// L...R
		//     i
		// 当前让 i -> [i] 进窗口 ， i 就是 r
		for (int R = 0; R < arr.length; R++) {
			// R -> 值  可以放在比他大的数后，或者空
			//
			while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
				qmax.pollLast();
			}
			qmax.addLast(R);
			// 数进来了
			// 如果窗口没有形成W的长度之前，不弹出数字的
			// R-w 窗口左位置，小于左边界的值移除掉
			if (qmax.peekFirst() == R - w) {
				qmax.pollFirst();
			}
			// 以上窗口更新做完了
			// 输出当前窗口最大值
			if (R >= w - 1) {
				res[index++] = arr[qmax.peekFirst()];
			}
		}
		return res;
	}

	/**
	 * 思路：
	 * 第一步： 先将小于当前值的下标全部从队列内移除
	 * 第二步： 将当前值下标添加进队列
	 * 第三步： 移除过期下标，也就是小于窗口左指针
	 * 第四步： 输出当前窗口最大值
	 */
	public static int[] myGetMaxWindow(int[] arr,int w){
		int[] ints = new int[arr.length - w + 1];
		if(arr.length==0 || w<1 || arr.length<w){
			return null;
		}
		int index = 0;
		LinkedList<Integer> linkedList = new LinkedList<>();
		for (int i = 0; i < arr.length; i++) {
			// 将较大值写入队列内，使队列保证从大到小顺序，头大尾小
			while (!linkedList.isEmpty() && arr[i]>= arr[linkedList.peekLast()]){
				linkedList.pollLast();
			}
			linkedList.addLast(i);
			// 移除队列内超出窗口范围的下标
			if(linkedList.peekFirst() == i-w){
				linkedList.pollFirst();
			}
			// 当满足窗口长度时，输出当前窗口内最大值
			if(i>=w-1){
				ints[index++] = arr[linkedList.peekFirst()];
			}

		}
		return ints;
	}

	public static int[] myGetMaxWindow1(int[] arr ,int w){
		if(arr==null || w<1 || arr.length<w){
			return null;
		}
		LinkedList<Integer> qmax = new LinkedList<>();
		int[] ints = new int[arr.length-w+1];
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			while (!qmax.isEmpty() && arr[qmax.peekLast()]<arr[i]){
				qmax.pollLast();
			}
			qmax.addLast(i);
			if(i-w == qmax.peekFirst()){
				qmax.pollFirst();
			}
			if(i>=w-1){
				ints[index++] = arr[qmax.peekFirst()];
			}
		}
		return ints;
	}

	// for test
	public static int[] rightWay(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		int L = 0;
		int R = w - 1;
		while (R < arr.length) {
			int max = arr[L];
			for (int i = L + 1; i <= R; i++) {
				max = Math.max(max, arr[i]);

			}
			res[index++] = max;
			L++;
			R++;
		}
		return res;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int testTime = 100000;
		int maxSize = 100;
		int maxValue = 100;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int w = (int) (Math.random() * (arr.length + 1));
//			int[] ans1 = getMaxWindow(arr, w);
			int[] ans1 = myGetMaxWindow1(arr, w);
			int[] ans2 = rightWay(arr, w);
			if (!isEqual(ans1, ans2)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
