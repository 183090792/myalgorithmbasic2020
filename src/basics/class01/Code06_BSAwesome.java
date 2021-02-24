package basics.class01;

/**
 * 局部最小值问题
 *
 * 当a[i]的前后两个元素都存在时，需要满足“a[i] < a[i-1]，且a[i] < a[i+1]”这个条件，
 * 但是如果a[i]是第一个元素或者是最后一个元素，那么只需要看一边。所以对于任何一个数组，”局部最小元素“一定是存在的
 */
public class Code06_BSAwesome {

	public static int getLessIndex(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1; // no exist
		}
		if (arr.length == 1 || arr[0] < arr[1]) {
			return 0;
		}
		if (arr[arr.length - 1] < arr[arr.length - 2]) {
			return arr.length - 1;
		}
		int left = 1;
		int right = arr.length - 2;
		int mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			if (arr[mid] > arr[mid - 1]) {
				right = mid - 1;
			} else if (arr[mid] > arr[mid + 1]) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		return left;
	}

}
