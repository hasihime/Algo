package hw_190809;

import java.util.Scanner;
/*
 * 3408. 세가지 합 구하기
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWEbPukqySUDFAWs
 *  300 ms 실행시간 427 코드길이 
 */
public class Solution_세가지합구하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {

			long n = sc.nextLong();
			long s1 = 0, s2 = 0, s3 = 0;

			s1 = (n) * (n + 1) / 2;

			s2 = n * (1 + (2 * n - 1)) / 2;

			s3 = n * (2 + (2 * n)) / 2;


			System.out.println("#" + t + " " + s1 + " " + s2 + " " + s3);
		}

	}

}
