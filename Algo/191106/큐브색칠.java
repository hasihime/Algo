import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class ť���ĥ {

	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			long linearr[] = new long[n];

			// a���� X���� ���� ���� ����
			for (int i = a; i < x; i++)
				linearr[(i - a) % n]++;

			// 0���� a-1���� ĥ��
			for (int i = a - 1; i >= 0; i--)
				linearr[(a - i) % n]++;


//			System.out.println(Arrays.toString(line));

			long sidearr[] = new long[n]; 	// ���� ������ �迭 ����
			long sidesum = 0; 				// ������ ���� ���� ����
			long sidemok = (y - b - 1) / n; // b+1���� Y���� ������ �ݺ��Ǵ� ���� ����
			long sidenmg = (y - b - 1) % n; // b+1���� Y���� �������� ����

			// �� �ٿ��� ���ü� �ִ� �� ���� ����
			// �׳� x�� �ϴϱ� Ʋ���� ���ɰ� ����
			for (int i = 0; i < n; i++) {
				sidesum += linearr[i];
			}

			for (int i = 0; i < n; i++) { // i��° ���ڴ� b�� line[i]�� ������ �տ� ���� ���� ���Ѵ�.
				sidearr[i] = (sidesum * sidemok) + linearr[i];
			}
			
			//�򺸴� ���� ���� ������ ���Ѵ�.
			for (int i = 1; i <= sidenmg; i++) { // 0�� ������ ����������� 1�� ���κ��� ����
				for (int j = 0; j < n; j++) { // ������ �ȵǴ� �������� ����Ѵ�.
					sidearr[(i + j) % n] += linearr[j]; // i��° ��+j��°�� n�� mod ���� �ε����� ���� ���� ���Ѵ�.
				}
			}

			// 0���� b���� ������ ����
			sidemok = (b) / n;
			sidenmg = (b) % n;

			for (int i = 0; i < n; i++) {
				sidearr[i] += (sidesum * sidemok);
			}
			for (int i = 1; i <= sidenmg; i++) {
				
				//i��° ���� modn�� �ε��� ���� ����
				for (int j = 0; j < n; j++) {
					sidearr[(i + j) % n] += linearr[j];
				}
			}

//			System.out.println(Arrays.toString(sidearr));

			long prismarr[] = new long[n]; // ���� ���� ���� ������ �迭 ����
			long prismsum = 0; // ���� ���� ���� ����
			long prismmok = (z - c - 1) / n; // c���� z���� ���� �ݺ��Ǵ� ���� ����
			long prismnmg = (z - c - 1) % n; // c���� z���� �������� ����

			// �� �鿡�� ���ü� �ִ� �� ���� ����
			// �׳� x*y�� �ϴϱ� Ʋ���� ���ɰ� ����
			for (int i = 0; i < n; i++) {
				prismsum += sidearr[i];
			}
			for (int i = 0; i < n; i++) {
				prismarr[i] += (prismsum * prismmok) + sidearr[i];
			}
			for (int i = 1; i <= prismnmg; i++) {
				for (int j = 0; j < n; j++) {
					prismarr[(i + j) % n] += sidearr[j];
				}
			}

			// 0���� c���� ���� ����
			prismmok = (c) / n;
			prismnmg = (c) % n;

			for (int i = 0; i < n; i++) {
				prismarr[i] += (prismsum * prismmok); 		// i��° ���ڴ� line[i]�� ���� ����*���� ���� ���� ���� ���Ѵ�.
			}
			for (int i = 1; i <= prismnmg; i++) { 			// 0�� ���� ����������� 1�� ����� ����
				for (int j = 0; j < n; j++) { 				// ������ �ȵǴ� �������� ����Ѵ�.
					prismarr[(i + j) % n] += sidearr[j]; 	// i��° ��+j������ n�� mod ���� �ε����� ���� ���� ���Ѵ�.
				}
			}

			for (int i = 0; i < n; i++) {
				sb.append(prismarr[i]);
				if (i < n - 1) {
					sb.append(" ");
				}
			}

			sb.append("\n");
		} // T��

		System.out.println(sb);
	}
}
