package hw_190809;

/*
 *  1860. 진기의 최고급 붕어빵 
 *  https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LsaaqDzYDFAXc
 *  664 ms 실행시간 1,271 코드길이 
 */
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution_붕어빵 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int N_peoplecount = sc.nextInt();
			int M_bangmaketime = sc.nextInt();
			int K_bangmakequan = sc.nextInt();

			List<Integer> arr2 = new LinkedList<Integer>();

			String result = null;
			int maxtime = 0;
			for (int i = 0; i < N_peoplecount; i++) {
				int t1 = sc.nextInt();
				arr2.add(t1);

				if (t1 > maxtime) {
					maxtime = t1;
				}
			}

			Collections.sort(arr2);
			int bang = 0;
			int nowpersoncount = 0;

			for (int i = 0; i <= maxtime; i++) {
				if (i % M_bangmaketime == 0 && i!=0) {
					bang += K_bangmakequan;
				}

				while (arr2.get(0) == i) {
					nowpersoncount++;
					arr2.remove(0);
					if (arr2.isEmpty()) {
						break;
					}
				}

				if (nowpersoncount > bang) {
					result = "Impossible";
					break;
				} else if (nowpersoncount <= bang) {
					bang -= nowpersoncount;
					nowpersoncount = 0;
					
				}
			}
			if (result == null) {
				result = "Possible";
			}
			System.out.println("#" + t + " " + result);
		}
	}
}
