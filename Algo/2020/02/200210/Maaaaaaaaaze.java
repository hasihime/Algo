import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Maaaaaaaaaze {

	private static BufferedReader br;
	private static StringTokenizer st;
	private static int[][][][] map;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
					//판번호,회전번호,가로,세로
		map = new int[5][4][5][5];

		input();
		maketurntable();
		makelayer(0, 0);

	}

	private static void maketurntable() {
		for (int k = 0; k < 5; k++) {
				rotate(k);		
		}
		
	}

	private static void rotate(int k) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[k][1][i][j]=map[k][0][4-j][i];
			}
		}
		System.out.println();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[k][2][i][j]=map[k][1][4-j][i];
			}
		}
		System.out.println();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[k][3][i][j]=map[k][2][4-j][i];
			}
		}
		System.out.println();
	}

	private static void makelayer(int cur, int target) {

	}

	private static void input() throws IOException {
		for (int k = 0; k < map.length; k++) {
			for (int i = 0; i < 5; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 5; j++) {
					map[k][0][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}

	}

}
