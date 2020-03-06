import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_2048easy {
	private static int n;
	private static int[][] map;
	private static int[] order;
	private static int[][] backmap;
	private static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		makepermu(0);
		System.out.println(result);
	}

	private static void makepermu(int target) {
		if (target == 5) {
//			System.out.println(Arrays.toString(order));
			go();
			restore();
			return;
		}
		for (int i = 0; i < 4; i++) {
			order[target] = i;
			makepermu(target + 1);
		}
	}

	private static void restore() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = backmap[i][j];
			}
		}

	}

	private static void go() {
		for (int i = 0; i < 5; i++) {
			move(order[i]);
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result = Math.max(result, map[i][j]);
			}
		}
	}

	private static void move(int dir) {
	
		boolean[][] visited = new boolean[n][n];
		// 0 위
		if (dir == 0) {
			for (int j = 0; j < n; j++) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				for (int i = 0; i < n; i++) {
					if (map[i][j] != 0) {
						list.add(map[i][j]);
						map[i][j] = 0;
					}
				}

				for (int idx = 0; idx < list.size(); idx++) {
					int cur = list.get(idx);
					for (int i = 0; i < n; i++) {
						if (map[i][j] == 0) {
							map[i][j] = cur;
							if(i!=0)
							visited[i-1][j]=true;
							break;
						}else if (map[i][j] == cur && !visited[i][j]) {
							map[i][j] = cur * 2;
							visited[i][j] = true;
							break;
						} 
					}
				}
			}
		}

		// 1 아래
		else if (dir == 1) {
			for (int j = 0; j < n; j++) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				for (int i = n - 1; i >= 0; i--) {
					if (map[i][j] != 0) {
						list.add(map[i][j]);
						map[i][j] = 0;
					}
				}

				for (int idx = 0; idx < list.size(); idx++) {
					int cur = list.get(idx);
					for (int i = n - 1; i >= 0; i--) {
						 if (map[i][j] == 0) {
								map[i][j] = cur;
								if(i!=n-1)
									visited[i+1][j]=true;
								break;
							} else if (map[i][j] == cur && !visited[i][j]) {
								map[i][j] = cur * 2;
								visited[i][j] = true;
								break;
							}
					}
				}
			}
		}
		// 2 왼
		else if (dir == 2) {
			for (int i = 0; i < n; i++) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				for (int j = 0; j < n; j++) {
					if (map[i][j] != 0) {
						list.add(map[i][j]);
						map[i][j] = 0;
					}
				}

				for (int idx = 0; idx < list.size(); idx++) {
					int cur = list.get(idx);
					for (int j = 0; j < n; j++) {
						if (map[i][j] == 0) {
							map[i][j] = cur;
							if(j!=0)
								visited[i][j-1]=true;
							break;
						} else if (map[i][j] == cur && !visited[i][j]) {
							map[i][j] = cur * 2;
							visited[i][j] = true;
							break;
						}
					}
				}
			}
		}
		// 3 오른쪽
		else if (dir == 3) {
			for (int i = 0; i < n; i++) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				for (int j = n - 1; j >= 0; j--) {
					if (map[i][j] != 0) {
						list.add(map[i][j]);
						map[i][j] = 0;
					}
				}

				for (int idx = 0; idx < list.size(); idx++) {
					int cur = list.get(idx);
					for (int j = n - 1; j >= 0; j--) {
						if (map[i][j] == 0) {
							map[i][j] = cur;
							if(j!=n-1)
								visited[i][j+1]=true;
							break;
						} else if (map[i][j] == cur && !visited[i][j]) {
							map[i][j] = cur * 2;
							visited[i][j] = true;
							break;
						}
					}
				}
			}
		}
	}

	private static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		backmap = new int[n][n];
		order = new int[5];
		result = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				backmap[i][j] = map[i][j];
			}
		}
	}
}
