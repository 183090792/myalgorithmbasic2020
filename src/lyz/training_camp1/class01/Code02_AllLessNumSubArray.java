package lyz.training_camp1.class01;


import java.util.LinkedList;

/**
 * 问题：
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 */
public class Code02_AllLessNumSubArray {

	public static int getNum(int[] arr, int num) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		LinkedList<Integer> qmin = new LinkedList<Integer>();
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		int L = 0;
		int R = 0;
		// [L..R) -> [0,0) 窗口内无数 [1,1)
		// [0,1) -> [0~0]
		int res = 0;
		while (L < arr.length) { // L是开头位置，尝试每一个开头

			// 如果此时窗口的开头是L,下面的while工作:R向右扩到违规为止

			while (R < arr.length) { // R是最后一个达标位置的再下一个
				while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
					qmin.pollLast();
				}
				qmin.addLast(R);
				// R -> arr[R],
				while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
					qmax.pollLast();
				}
				qmax.addLast(R);

				if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
					break;
				}
				R++;
			}

			// R是最后一个达标位置的再下一个，第一个违规的位置
			res += R - L;

			if (qmin.peekFirst() == L) {
				qmin.pollFirst();
			}
			if (qmax.peekFirst() == L) {
				qmax.pollFirst();
			}

			L++;

		}
		return res;
	}

	/**
	 * 思路：
	 * 将数组依次往后组合，找出最大的合规子数组，然后将子数组拆分为更小额，累加
	 */
	public static int myGetNum(int[] arr, int num){
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int result = 0;
		LinkedList<Integer> max = new LinkedList<>();
		LinkedList<Integer> min = new LinkedList<>();
		int r = 0;
		int l = 0;
		while (l < arr.length) {
			while (r < arr.length){
				while (!max.isEmpty() && arr[r] >= arr[max.peekLast()]){
					max.pollLast();
				}
				max.addLast(r);
				while (!min.isEmpty() && arr[r]<=arr[min.peekLast()]){
					min.pollLast();
				}
				min.addLast(r);
				if(arr[max.peekFirst()] - arr[min.peekFirst()] > num){
					break;
				}
				r++;
			}
			result+=r-l;
			if(min.peekFirst() == l){
				min.pollFirst();
			}
			if(max.peekFirst() == l){
				max.pollFirst();
			}
			l++;
		}
		return result;
	}

	public static int myGetNum1(int[] arr,int num){
		

		return 1;
	}

	// for test
	public static int[] getRandomArray(int len) {
		if (len < 0) {
			return null;
		}
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * 10);
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
//		int[] arr = {9,4,3,2};
		int[] arr = getRandomArray(30);
		int num = 5;
		printArray(arr);
		System.out.println(getNum(arr, num));
		System.out.println(myGetNum(arr, num));

	}

}
