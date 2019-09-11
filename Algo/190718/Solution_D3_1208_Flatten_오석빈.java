package hw_day190718;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_D3_1208_Flatten_오석빈 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		for (int i = 1; i <= 10; i++) {
			
			int box[] = new int[100];
			int dump = sc.nextInt();
			int height = 0;

			for (int j = 0; j < box.length; j++) {
				box[j] = sc.nextInt();
			}
			Arrays.sort(box);

			for (int j = 0; j < dump; j++) {
				// 박스를 하나씩 주고 받는다.
				box[99] = box[99] - 1;
				box[0] = box[0] + 1;
				Arrays.sort(box);
			}
			height = box[99] - box[0];
			System.out.println("#" + i + " " + height);

		}

	}
}
