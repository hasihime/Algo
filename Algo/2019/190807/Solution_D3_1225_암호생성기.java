package hw_day190807;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_D3_1225_암호생성기_오석빈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated method stub
		for (int t = 1; t <= 10; t++) {

			int n = sc.nextInt();
			Queue<Integer> q1 = new LinkedList<Integer>();
			int[] arr = new int[8];
			for (int i = 0; i < 8; i++) {
				q1.offer(sc.nextInt());
			}

			boolean flag = true;
			for (int i = 0; flag; i++) {
				for (int j = 1; j <= 5; j++) {
					int temp = q1.poll() - j;

					if (temp <= 0) {
						q1.offer(0);
						flag = false;
						break;
					} else
						q1.offer(temp);
				}
			}
			System.out.print("#" + n + " ");
			int[] temp = new int[8];
			for (int i = 0; i < 8; i++) {
				temp[i] = q1.poll();
			}
			for (int i = 0; i < 8; i++) {
				System.out.print(temp[i] + " ");
			}

			System.out.println();
		}
	}

}
