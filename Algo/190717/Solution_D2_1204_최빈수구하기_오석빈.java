package hw_day190717;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_D2_1204_최빈수구하기_오석빈 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		for (int i = 1; i <= t; i++) {
			int count = 0, max_count = 0, max_score=0;
			int s = sc.nextInt();
			int test[] = new int[101];
			for (int j = 0; j < 1000; j++) {
				int result = sc.nextInt();
				test[result]++;
			}
//			Arrays.sor
			for (int j = 0; j < test.length; j++) {
				count = test[j];
				if (count >= max_count) {
					max_count = count;
					max_score=j;
				}
	
			}
		System.out.println("#"+s+" "+max_score);
		}

	}
}