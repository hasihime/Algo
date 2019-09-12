package hw_190809;

import java.util.Scanner;

/*
 *  8104. 조 만들기
 *   https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWwXCn2KQjEDFATu
 *  202 ms 실행시간 870 코드길이 
 */
public class Solution_조만들기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			int K = sc.nextInt();

			int student = N * K;

			int[][] group = new int[N][K];
			int [] result=new int[K];
			int temp=1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <K; j++) {
					group[i][j]=temp;
					temp++;
				}
			}
			
			for (int i = 0; i < N; i++) {
				if (i%2==0) {
					for (int j = 0; j <K; j++) {
						result[j]+=group[i][j];
					}
				}
				if (i%2==1) {
					for (int j = 0; j <K; j++) {
						result[j]+=group[i][K-j-1];
					}
				}
			}
			
			System.out.print("#"+t);
			for (int i = 0; i < K; i++) {
				System.out.print(" "+result[i]);
			}
            System.out.println();
		}
	}

}
