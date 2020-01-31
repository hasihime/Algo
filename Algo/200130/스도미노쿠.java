import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 스도미노쿠 {

	private static int[][] map;
	private static int N;
	private static StringTokenizer st;
	private static BufferedReader br;
	private static int[] blankarr;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static StringBuffer sb;
	private static boolean flag;
	private static HashMap<int[], Integer> hm;
	private static HashMap<String, Integer> hm1;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuffer();
	
//		hm= new HashMap<int[], Integer>();


		
		int T = 1;
		while (true) {
//			System.out.println("!");
			hm1= new HashMap<String, Integer>();
			for (int i = 1; i <= 9; i++) {
				for (int j = 1; j <=9; j++) {
					if (i==j)continue;
					int[] temp=new int[2];
//					temp[0]=i;
//					temp[1]=j;
//					hm.put(temp, 1);
					hm1.put(i+""+j, 1);
				}
			}
			
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			blankarr = new int[81];

			sb.append("Puzzle ").append(T).append("\n");
			map = new int[9][9];
			makemap();

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (map[i][j] == 0) {
						blankarr[i * 9 + j] = -1;
					} else {
						blankarr[i * 9 + j] = 1;
					}
				}
			}
			// 만들자.
			flag = false;
			makeblock(0);
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			T++;
		}
		System.out.println(sb);
	}

	private static void makeblock(int idx) {
		if (flag) {
			return;
		}

		for (int i = idx; i <= 81; i++) {
			if (i == 81) {
				flag = true;
				return;
			}
			if (blankarr[i] == -1) {
				// 블록을 넣자.
				for (int d = 0; d < 4; d++) {
					if (ispossible(i / 9, i % 9, d)) {
						// 숫자 조합을 만들자.
						int nr = i / 9 + dr[d];
						int nc = i % 9 + dc[d];
						for (int n1 = 1; n1 <= 9; n1++) {
							if (!checkgaro(n1, i / 9) || !checksero(n1, i % 9) || !checkarea(n1, i / 9, i % 9))
								continue;
							for (int n2 = 1; n2 <= 9; n2++) {
								if (n1 == n2)
									continue;
								if (!checkgaro(n2, nr) || !checksero(n2, nc) || !checkarea(n2, nr, nc))
									continue;
//								int[] temp1=new int[2];
//								int[] temp2=new int[2];
//								temp1[0]=n1;
//								temp1[1]=n2;
//								temp2[0]=n2;
//								temp2[1]=n1;
//								if (hm.get(temp2)==0||hm.get(temp1)==0) continue;
								if (hm1.get(n1+""+n2)==0||hm1.get(n2+""+n1)==0) continue;
								
								// 숫자 조합이 나오면 넣고 다음 재귀로
								putblock(i / 9, i % 9, 1, d, n1, n2);
								makeblock(i + 1);
								if (flag) {
									return;
								}
								putblock(i / 9, i % 9, -1, d, n1, n2);
							}
						}
					}
				}
				return;
			}

		}

	}

	private static boolean checkarea(int curnum, int row, int col) {
		for (int i = (row / 3) * 3; i < (row / 3) * 3 + 3; i++) {
			for (int j = (col / 3) * 3; j < (col / 3) * 3 + 3; j++) {
				if (map[i][j] == curnum)
					return false;
			}
		}
		return true;
	}

	private static boolean checksero(int curnum, int col) {
		for (int i = 0; i < 9; i++) {
			if (map[i][col] == curnum)
				return false;
		}
		return true;
	}

	private static boolean checkgaro(int curnum, int row) {
		for (int i = 0; i < 9; i++) {
			if (map[row][i] == curnum)
				return false;
		}
		return true;
	}

	// 그 맵에 넣을수 있는지.
	private static boolean ispossible(int row, int col, int d) {
		int nr = row + dr[d];
		int nc = col + dc[d];
		if (nr >= 0 && nr < 9 && nc >= 0 && nc < 9 && map[nr][nc] == 0) {
			return true;
		}
		return false;
	}

	private static void putblock(int row, int col, int visited, int dir, int num1, int num2) {
		int nr = row + dr[dir];
		int nc = col + dc[dir];
		hm1.put(num1+""+num2, hm1.get(num1+""+num2)-visited);
		hm1.put(num2+""+num1, hm1.get(num2+""+num1)-visited);
		if (visited==1) {
			map[row][col] = num1;
			map[nr][nc] = num2;
		}else {
			map[row][col] = 0;
			map[nr][nc] = 0;
		}

//		int[] temp=new int[2];
//		temp[0]=num1;
//		temp[1]=num2;
//		hm.put(temp, hm.get(temp)-visited);

//		temp[0]=num2;
//		temp[1]=num1;
//		hm.put(temp, hm.get(temp)-visited);
		blankarr[row * 9 + col] = visited;
		blankarr[nr * 9 + nc] = visited;
	}

	private static void makemap() throws IOException {
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num1 = Integer.parseInt(st.nextToken());
			String sn1 = st.nextToken();
			int rn1 = sn1.substring(0, 1).charAt(0) - 'A';
			int cn1 = Integer.parseInt(sn1.substring(1)) - 1;
			map[rn1][cn1] = num1;
			
			int num2 = Integer.parseInt(st.nextToken());
			String sn2 = st.nextToken();
			int rn2 = sn2.substring(0, 1).charAt(0) - 'A';
			int cn2 = Integer.parseInt(sn2.substring(1)) - 1;
			map[rn2][cn2] = num2;
//			int[] temp=new int[2];
//			temp[0]=num1;
//			temp[1]=num2;
//			hm.put(temp, 0);
//			temp[0]=num2;
//			temp[1]=num1;
//			hm.put(temp, 0);		
			hm1.put(num1+""+num2, 0);
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= 9; i++) {
			String sn1 = st.nextToken();
			int rn1 = sn1.substring(0, 1).charAt(0) - 'A';
			int cn1 = Integer.parseInt(sn1.substring(1)) - 1;
			map[rn1][cn1] = i;
		}

	}

}
