package hw_day190814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution_D4_1233_사칙연산유효성검사_오석빈 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Scanner sc = new Scanner(System.in);
		for (int i = 1; i <= 10; i++) {

			int nodecount = Integer.parseInt(br.readLine());
int result=0;
			if (nodecount%2=0) {
				
			}
			char[] arr = new char[nodecount + 1];
			boolean[] arrleft = new boolean[nodecount + 1];
			boolean[] arrright = new boolean[nodecount + 1];
			for (int j = 1; j < nodecount + 1; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				int n = Integer.parseInt(st.nextToken());
				arr[n] = st.nextToken().charAt(0);
				if (st.hasMoreTokens()) {
					int left = sc.nextInt();
					arrleft[n] = true;
				}
				if (st.hasMoreTokens()) {
					int right = sc.nextInt();
					arrright[n] = true;
				}
			}
			
			
			for (int j = nodecount; j > 0; j--) {

				if (arrleft[j] == false || arrright[j]
						||arrleft[j]=='+'||arrleft[j]=='-'||arrleft[j]=='*'||arrleft[j]=='-') {
					
				}

				switch (arr[j]) {
				case '+':
				case '-':
				case '*':
				case '/':
					if (arrleft[j]) {

					}
					break;

				default:
					break;
				}

				if (arrleft[i] == true) {

				}
			}

		}
	}

}
