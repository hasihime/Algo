package binarysearch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Binarysearch {
	public static void main(String[] args) {

//		int[] arr= {1,2,9,78,124};
//		int target=11;
//		int result=Solution(arr,target);
		ArrayList<TargetArray> targetList = new ArrayList<TargetArray>();

		targetList.add(new TargetArray(new int[] { 1, 2, 9, 78, 124 }, 2, 1));
		targetList.add(new TargetArray(new int[] { 1, 2, 9, 78, 124 }, 11, -1));
		targetList.add(new TargetArray(new int[] {}, 2, -1));

		for (int i = 0; i < targetList.size(); i++) {
			int[] curArr = targetList.get(i).getArr();
			int curTarget = targetList.get(i).target;
			int ExtectResult = targetList.get(i).result;
//			int RealResult=Solution(curArr, curTarget);
			int RealResult = binarySearch(curArr, curTarget);
			if (ExtectResult == RealResult) {
				System.out.printf("테스트 결과 :true \n 예상 값 : %d 결과 값 %d \n", ExtectResult, RealResult);
			} else {
				System.out.printf("테스트 결과 :false \n 예상 값 : %d 결과 값 %d \n", ExtectResult, RealResult);
			}

		}

	}

	private static int binarySearch(int[] arr, int target) {
		int result = -1;

		int L = 0;
		int R = arr.length - 1;
		int mid = (L + R) / 2;

		result=go(arr, L, R, target, result);
//		while (L <= R) {
//			if (arr[mid] == target) {
//				result = mid;
//				break;
//			} else {
//				if (arr[mid] < target) {
//					L = mid + 1;
//				} else {
//					R = mid - 1;
//				}
//			}
//			mid = (L + R) / 2;
//		}

		return result;
	}

	private static Integer go(int[] arr, int l, int r, int target, int result) {
		int mid = (l + r) / 2;
		if (target == arr[mid]) {
			result = mid;
			return result;
		}
		if (l >= r) {
			return result;
		}
		
		if (arr[mid] < target) {
			result=go(arr, mid + 1, r, target, result);
			
		} else {
			result=go(arr, l, mid - 1, target, result);
				
		}
		return result;
	}

	private static int Solution(int[] arr, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int result = -1;
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], i);
		}

		if (map.containsKey(target)) {
			result = map.get(target);
		} else {
			result = -1;
		}

		return result;
	}
}
