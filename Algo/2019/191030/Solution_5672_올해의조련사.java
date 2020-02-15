import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_5672_올해의조련사 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			int N=n;
			char init[] = new char[n];
			char sort[] = new char[n];

			for (int i = 0; i < n; i++) {
				init[i] = br.readLine().charAt(0);
			}

			int i = 0;
			int headindex = 0;
			int tailindex = n - 1;
			while (n != 0) {
				char f = init[headindex];
				char r = init[tailindex];

				if (f != r) {
					if (f < r) {
						sort[i] = f;
						headindex++;
					} else {
						sort[i] = r;
						tailindex--;
					}
					i++;
					n--;
					continue;
				} else {

					boolean flag = true;
					int ti = 1;
					while (flag) {
						if (n > 3) {

							char ff = init[headindex + ti];
							char rr = init[tailindex - ti];
							if (ff == rr) {
								if (headindex + ti >= tailindex - ti) {
									sort[i] = r;
									tailindex--;
									i++;
									n--;
									break;
								}
								ti++;
								continue;

							} else {
								if (ff < rr) {
									sort[i] = f;
									headindex++;
								} else {
									sort[i] = r;
									tailindex--;
								}
								i++;
								n--;
								break;
							}
						}else if(n<=3){
							sort[i] = r;
							tailindex--;
							i++;
							n--;
							break;
						}
					}
				}
			}

			
			System.out.print("#"+t+" ");
			for (int j = 0; j < N; j++) {
				System.out.print(sort[j]);
			}
			System.out.println();
		}

	}

}
