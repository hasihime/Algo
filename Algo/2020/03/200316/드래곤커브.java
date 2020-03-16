import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 드래곤커브 {
	private static boolean[][] map;
	private static int y;
	private static int x;
	private static int dir;
	private static int gen;
	private static ArrayList<Integer> dlist;
	private static int result;

	// https://www.acmicpc.net/problem/15685
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		getrec();
		System.out.println(result);
	}

	private static void getrec() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j]&&map[i+1][j]&&map[i][j+1]&&map[i+1][j+1]) {
					result++;
				}
			}
		}
		
	}

	private static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new boolean[101][101];
		result=0;
		int n = Integer.parseInt(br.readLine());
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			gen = Integer.parseInt(st.nextToken());

			dlist = new ArrayList<Integer>();
			dlist.add(dir);
			getdir();//방향을 다 집어 넣고
			draw();
		}

	}

	private static void draw() {
		map[x][y]=true;
		for (int i = 0; i < dlist.size(); i++) {
			int curd=dlist.get(i);
			switch (curd) {
			case 0://방향이 오른쪽
				map[++x][y]=true;
				break;
			case 1://방향이 위
				map[x][--y]=true;
				break;
			case 2://방향이 왼쪽
				map[--x][y]=true;
				break;
			case 3://방향이 아래
				map[x][++y]=true;
				break;
			}
		}
		
	}

	private static void getdir() {
		while (gen-- > 0) {
			int size = dlist.size() - 1;
			for (int i = size; i >= 0; i--) {
				int curd = dlist.get(i);
				curd = (curd + 1) % 4;
				dlist.add(curd);
			}
		}

	}

}
