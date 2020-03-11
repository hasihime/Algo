import java.util.Arrays;
import java.util.Scanner;

public class 후보추천하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int t = sc.nextInt();
		int[] frame = new int[n];
		int[] cnt = new int[n+1];
		int[] year = new int[n];
		int[] input = new int[t];

		for (int i = 0; i < t; i++) {
			input[i] = sc.nextInt();
		}

		int jj = 0;
		for (int i = 0; i < t; i++) {
			int cur = input[i];
			boolean flag = true;
			
			
			for (int j = 0; j < n; j++) {
				if (frame[j] == 0) {
					frame[j] = cur;
					jj++;
					flag = false;
					for (int k = 0; k < jj; k++) {
						year[k]++;
					}
					break;
				} else if (frame[j] == cur) {
					cnt[j]++;
					flag = false;
					break;
				}
			}
			if (!flag)
				continue;
			// 여기 내려왔다는 것은 빈 칸이 없다는 것.
			int min = 1000;
			int idx = n-1;
			for (int j = n -1; j >= 0; j--) {
				if (min > cnt[j]) {
					min = cnt[j];
					idx = j;
				} else if (min == cnt[j]) {
					idx = year[idx] > year[j] ? idx : j;
				}
			}
			frame[idx] = cur;
			cnt[idx] = 0;
			year[idx] = 0;
			for (int j = 0; j < n; j++) {
				year[j]++;
			}
		}
		Arrays.sort(frame);
		for (int i = 0; i < n; i++) {
			if (i!=n-1) {
				System.out.print(frame[i] + " ");
			}else {
				System.out.print(frame[i]);
			}
		}
	}

}
