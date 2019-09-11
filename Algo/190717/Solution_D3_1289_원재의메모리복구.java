
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Solution_D3_1289_원재의메모리복구 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
//				Scanner sc = new Scanner(System.in);
//		int T = sc.nextInt();
		int T=Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			char[] input = in.readLine().toCharArray();
			int N = input.length, count = 0;
			char[] init = new char[N];
			Arrays.fill(init, '0');

			for (int i = 0; i < N; i++) { // input 기준으로 한 비트씩 앞쪽에서 검사
				if (input[i] == init[i])
					continue;// 고칠필요 없는 비트이면 건너뜀

				for (int j = i; j < N; j++) {
					init[j] = input[i];
				}
				count++; //고쳤으니 카운트 증가
			}
			System.out.println("#"+t+ " "+count);
		}
	}

}
