import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 특이한자석 {

	static boolean m[][];
	static boolean checked[];
	static int dirarr[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int result = 0;
			m = new boolean[4][8];
			int rotatecnt = Integer.parseInt(br.readLine());

			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 8; j++) {
					if (st.nextToken().equals("1")) {
						m[i][j] = true;
					}
				}
			}

			for (int i = 0; i < rotatecnt; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int selm = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());

				checked = new boolean[4];
				dirarr = new int[4];

				dfs(selm, dir);

				for (int j = 0; j < 4; j++) {
					switch (dirarr[j]) {
					case -1:
						boolean temp[] = new boolean[8];
						temp[7] = m[j][0];
						for (int k = 1; k < 8; k++) {
							temp[k - 1] = m[j][k];
						}
						m[j] = temp;
						break;

					case 1:
						boolean temp1[] = new boolean[8];
						temp1[0] = m[j][7];
						for (int k = 0; k < 7; k++) {
							temp1[k + 1] = m[j][k];
						}
						m[j] = temp1;
						break;
					}
				}
//      
//                    for (boolean[] b : m) {
//        				System.out.println(Arrays.toString(b));
//        			}
			}

			for (int i = 0; i < 4; i++) {
				if (m[i][0]) {
					result += Math.pow(2, i);
				}
			}
			System.out.println("#" + t + " " + result);
		}

	}

	private static void dfs(int selm, int dir) {
		if (selm < 0 || selm > 3 || checked[selm]) {
			return;
		}

		dirarr[selm] = dir;
		checked[selm] = true;

		int Lselm = selm - 1;

		if (Lselm >= 0 && !checked[Lselm]) {

			if (m[Lselm][2] != m[selm][6]) {
				dirarr[Lselm] = dir * (-1);

				// checked[Lselm] = true;
				dfs(Lselm, dir * (-1));
			}
		}

		int Rselm = selm + 1;
		if (Rselm < 4 && !checked[Rselm]) {

			if (m[selm][2] != m[Rselm][6]) {
				dirarr[Rselm] = dir * (-1);

				// checked[Rselm] = true;
				dfs(Rselm, dir * (-1));
			}
		}
	}

}