package hw_190815;

import java.util.Scanner;
import java.math.*;
//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=407&sca=50
public class HW_맛있는음식 {
	static int n;
	static int sour = 1;
	static int spicy = 0;
	static int min = 100000000;
	static int finalmin=2000000000;
	static int[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n][2];

		for (int i = 0; i < n; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		findmin(arr, 0);
		System.out.println(finalmin);

	}

	private static void findmin(int[][] arr2, int i) {
		if (i > n-1 || sour > 1000000000 || spicy > 1000000000) {
			if (finalmin>min) {
				finalmin=min;
			}
			min=100000000;
			sour = 1;
			spicy = 0;
			return ;
		}
		for ( ; i < n; i++) {
			for (int j=i+1; j <= n; j++) {
			sour *= arr[i][0];
			spicy += arr[i][1];
//			if (min > (int) Math.abs(sour - spicy)) {
				min = (int) Math.abs(sour - spicy);
//			}
			
				findmin(arr2, j);
			}
			
		}

	}
}
