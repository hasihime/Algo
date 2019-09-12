package hw_190809;

import java.util.Scanner;
/*
 *  8016. 홀수 피라미드
 *  https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWvzGUKKPVwDFASy
 *  144 ms 실행시간 336 코드길이 
 */
public class Solution_홀수피라미드 {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		int T=sc.nextInt();
		for (int t = 1; t <=T; t++) {
			Long N=(long) sc.nextInt();
			Long R=(N*N)*2-1;
			Long L=((N-1)*(N-1)+1)*2-1;
			
			
			System.out.println("#"+t+" "+L+" "+R);

		} 

	}

}
