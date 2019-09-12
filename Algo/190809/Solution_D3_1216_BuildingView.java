package hw_190809;

import java.util.Scanner;

/*
 * 1206. [S/W 문제해결 기본] 1일차 - View 
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV134DPqAA8CFAYh
 *   35,440 kb 메모리  206 ms 실행시간 1,265 코드길이 
 *
 *
 */
public class Solution_BuildingView {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {
			int building = sc.nextInt();
			int view = 0;
			int arr[] = new int[building];
			for (int j = 0; j < building; j++) {
				arr[j] = sc.nextInt();

			}

			for (int i = 0; i < building; i++) {

				if (i <= 1) {
					if (arr[i] >= arr[i + 1] && arr[i] >= arr[i + 2]) {
						if (arr[i + 1] >= arr[i + 2]) {
							view += arr[i] - arr[i + 1];
						} else {
							view += arr[i] - arr[i + 2];
						}
					}

				} else if (i >= building - 2) {

					if (arr[i] >= arr[i - 2] && arr[i] >= arr[i - 2]) {
						if (arr[i - 1] >= arr[i - 2]) {
							view += arr[i] - arr[i - 1];
						} else {
							view += arr[i] - arr[i - 2];
						}
					}
				} else {
					
					if (arr[i] > arr[i - 2] && arr[i] > arr[i - 1] &&
							arr[i] > arr[i + 1] && arr[i] > arr[i + 2]) {
						int max = 0;
						max = arr[i - 2];

						if (max < arr[i - 1]) {
							max = arr[i - 1];

						} if (max <arr[i + 1]) {
							max = arr[i + 1];

						} if (max <arr[i + 2]) {
							max = arr[i + 2];
						}
						view += arr[i] - max;
					}
				}
			}
			System.out.println("#" + t + " " + view);
		}
	}
}
