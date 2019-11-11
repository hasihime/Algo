import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 추억의2048 {
	static int map[][];
	static int nmap[][];
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			nmap = new int[N][N];
			String inputdir = st.nextToken();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			move(inputdir);

			System.out.println("#" + t);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (j == 0)
						System.out.print(nmap[i][j]);
					else {
						System.out.print(" " + nmap[i][j]);
					}
				}

				System.out.println();

			}
		}

	}

	private static void move(String inputdir) {
		int dir = 0;
		if (inputdir.startsWith("u")) {
			dir = 1;
		} else if (inputdir.startsWith("r")) {
			dir = 2;
		} else if (inputdir.startsWith("d")) {
			dir = 3;
		} else if (inputdir.startsWith("l")) {
			dir = 4;
		}

		List<Integer> temp;
		if (dir == 1) {
			for (int j = 0; j < N; j++) {
				temp = new ArrayList<Integer>();
				for (int i = 0; i < N; i++) {
					if (map[i][j] != 0)
						temp.add(map[i][j]);
				}

				merge(temp);
				for (int i = 1; i < temp.size(); i++) {
					if (temp.get(i) == temp.get(i - 1)) {
						temp.set(i - 1, temp.get(i - 1) * 2);
						temp.remove(i);
					}
				}
				for (int i = 0; i < N; i++) {
					if (!temp.isEmpty()) {
						nmap[i][j] = temp.get(0);
						temp.remove(0);
						
					} else {
						break;
					}
				}
			}
		} else if (dir == 2) {
			for (int i = 0; i < N; i++) {
				temp = new ArrayList<Integer>();
				for (int j = N - 1; j >= 0; j--) {
					if (map[i][j] != 0)
						temp.add(map[i][j]);
				}

				for (int j = 1; j < temp.size(); j++) {
					if ( temp.get(j) == temp.get(j - 1)) {
						temp.set(j - 1, temp.get(j - 1) * 2);
						temp.remove(j);
					}
				}
				for (int j = N - 1; j >= 0; j--) {
					if (!temp.isEmpty()) {
						nmap[i][j] = temp.get(0);
						temp.remove(0);
					} else {
						break;
					}
				}
			}
		} else if (dir == 3) {
			for (int j = 0; j < N; j++) {
				temp = new ArrayList<Integer>();
				for (int i = N - 1; i >= 0; i--) {
					if (map[i][j] != 0)
						temp.add(map[i][j]);
				}

				for (int i = 1; i < temp.size(); i++) {
					if ( temp.get(i) == temp.get(i - 1)) {
						temp.set(i - 1, temp.get(i - 1) * 2);
						temp.remove(i);
					}
				}
				for (int i = N - 1; i >= 0; i--) {
					if (!temp.isEmpty()) {
						nmap[i][j] = temp.get(0);
						temp.remove(0);
					} else {
						break;
					}

				}

			}
		} else if (dir == 4) {
			for (int i = 0; i < N; i++) {
				temp = new ArrayList<Integer>();
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0)
						temp.add(map[i][j]);
				}

				for (int j = 1; j < temp.size(); j++) {
					if ( temp.get(j) == temp.get(j - 1)) {
						temp.set(j - 1, temp.get(j - 1) * 2);
						temp.remove(j);
					}
				}
				for (int j = 0; j < N; j++) {
					if (!temp.isEmpty()) {
						nmap[i][j] = temp.get(0);
						temp.remove(0);
					} else {
						break;
					}
				}
			}
		}

	}

	private static List<Integer> merge(List<Integer> temp) {
		List<Integer> list2=new ArrayList<Integer>();
		
		
		int size=temp.size();
		
		while (i<size) {
			
		}
		
		return list2;
	}
}
