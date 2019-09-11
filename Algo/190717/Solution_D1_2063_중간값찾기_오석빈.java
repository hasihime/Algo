
import java.util.Scanner;

public class Solution_D1_2063_중간값찾기_오석빈 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int test[] = new int[n];
		for (int i = 0; i < n; i++) {
			int s = sc.nextInt();
			test[i] = s;
		}
		for (int i = 0; i < n; i++) {

			for (int j = i+1 + 1; j < n; j++) {

				if (test[i] > test[j]) {
					int temp = test[j];
					test[j] = test[i];
					test[i] = temp;
				}

			}

		}

		System.out.println(test[(n / 2) ]);
	}

}
